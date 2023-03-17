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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


            // Subscribe to the topic
            int qos = 2;
            client.subscribe(significant_vehicle_pos, qos);



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
        cause.printStackTrace();
    }


    @Override
    public void messageArrived(String topic, MqttMessage message) {
        ObjectMapper mapper = new ObjectMapper();
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            JsonNode root = mapper.readTree(message.toString());
            int oper = root.path("VP").path("oper").asInt();
            int veh = root.path("VP").path("veh").asInt();
            String oper_veh = String.format("%d_%d", oper, veh);

            // Extract the nextStop value from the topic URL, since it is not included in the message
            String nextStop = getNextStopFromUrl(topic);

            // Deserialize the JSON message into a VehiclePosition object
            VehiclePosition currentVehiclePosition = mapper.readValue(root.path("VP").toString(), VehiclePosition.class);


            currentVehiclePosition.setNextStop(nextStop);

            VehiclePosition existingVehiclePosition = session.load(VehiclePosition.class, oper_veh);
            if (existingVehiclePosition != null) {
                // Check if the existing document is less recent than the received message
                if (existingVehiclePosition.getTsi() > currentVehiclePosition.getTsi()) {
                    // Document is more recent than message, do not save
                    return;
                } else {
                    // Document is less recent than message, save
                    session.advanced().evict(existingVehiclePosition); // Evict the existing document from the session
                    session.store(currentVehiclePosition, oper_veh);
                    session.saveChanges();
                }
            } else {
                // If no existing document is found, store the new VehiclePosition
                session.store(currentVehiclePosition, oper_veh);
                session.saveChanges();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract the nextStop value from the topic URL
    private String getNextStopFromUrl(String topicUrl) {
        // Only extract the nextStop value from the topic URL
        Pattern pattern = Pattern.compile("/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+/([^/]+)/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+/[^/]+");
        // Match the pattern against the topic URL
        Matcher matcher = pattern.matcher(topicUrl);

        // If a match is found, return the nextStop value
        if (matcher.find()) {
            return matcher.group(1);
        }

        // If no match is found, return null
        return null;
    }



    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used in this code test, part of interface
    }


}





