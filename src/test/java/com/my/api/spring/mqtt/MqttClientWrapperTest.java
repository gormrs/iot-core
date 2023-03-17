

import com.my.api.spring.mqtt.MqttClientWrapper;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MqttClientWrapperTest {
    private MqttClientWrapper clientWrapper;

    @Before
    public void setUp() {
        clientWrapper = new MqttClientWrapper();
    }

    @Test
    public void testConnection() {
        assertTrue(clientWrapper.isConnected());

    }

    @Test
    public void testSubscription() throws MqttException {
        clientWrapper.subscribeToTopic("/hfp/v2/journey/", 1);
        assertTrue(clientWrapper.isSubscribed("/hfp/v2/journey/"));
    }


    @After
    public void tearDown() throws MqttException {
        if (clientWrapper != null) {
            clientWrapper.disconnect();
        }
    }
}
