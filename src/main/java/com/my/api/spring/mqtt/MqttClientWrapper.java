package com.my.api.spring.mqtt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.api.spring.document.DocumentStoreHolder;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.commands.GetDocumentsCommand;
import net.ravendb.client.documents.session.DocumentInfo;
import net.ravendb.client.documents.session.IDocumentSession;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.io.IOException;

public class MqttClientWrapper implements MqttCallback {
    private MqttClient client;
    final String brokerUrl = "tcp://mqtt.hsl.fi:1883"; // no tls for this port, cant be used in prod
    final String significant_vehicle_pos = "/hfp/v2/journey/ongoing/vp/bus/+/+/+/+/+/+/+/0/#"; // gets location of all buses inroute

    public MqttClientWrapper() {
        try {
            String persistenceDirectory = System.getProperty("java.io.tmpdir");
            MqttClientPersistence persistence = new MqttDefaultFilePersistence(persistenceDirectory);
            client = new MqttClient(brokerUrl, MqttClient.generateClientId(), persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setKeepAliveInterval(30);
            client.setCallback(this); // Set the callback
            client.connect(options);
            System.out.println("Connected to MQTT broker at " + client.getServerURI());

            // Subscribe to the topic
            int qos = 2;
            client.subscribe(significant_vehicle_pos, qos);
            System.out.println("Subscribed to topic: " + significant_vehicle_pos);


        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void subscribeToTopic(String topic, int qos) throws MqttException {
        client.subscribe(topic, qos);
    }

    public boolean isSubscribed(String topic) {
        return client.getTopic(topic) != null;
    }

    public void disconnect() throws MqttException {
        client.disconnect();
    }


    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost: " + cause.getMessage());
        cause.printStackTrace();
    }


    @Override
    public void messageArrived(String topic, MqttMessage message) {
        // System.out.println("Message arrived on topic " + topic + ": " + new String(message.getPayload()));
        ObjectMapper mapper = new ObjectMapper();
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            JsonNode root = mapper.readTree(message.toString());
            int oper = root.path("VP").path("oper").asInt();
            int veh = root.path("VP").path("veh").asInt();
            String oper_veh = String.format("%d_%d", oper, veh);

            // Check if document exists
            JsonNode compareToRoot = session.load(JsonNode.class, oper_veh);
            if (compareToRoot != null) {
                // check if it is less recent than message
                if (compareToRoot.path("VP").path("tst").asLong() > root.path("VP").path("tst").asLong()) {
                    // Document is more recent than message, do not save
                    System.out.println("Document is more recent than message, not saving");
                    return;
                } else {
                    // Document is less recent than message, save
                    System.out.println("Document is less recent than message, saving");
                    session.advanced().evict(compareToRoot); // Evict the existing document from the session
                    session.store(root, oper_veh);
                    session.saveChanges();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used in this code test
    }


}





