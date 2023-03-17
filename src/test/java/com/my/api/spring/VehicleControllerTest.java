package com.my.api.spring;

import com.my.api.spring.document.DocumentStoreHolder;
import com.my.api.spring.mqtt.VehiclePosition;
import net.ravendb.client.documents.session.IDocumentSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@SpringJUnitConfig(Application.class)
public class VehicleControllerTest {

    @Test
    public void testPingPong() {
        VehicleController controller = new VehicleController();
        String result = controller.pingPong();
        assertEquals("pong", result);
    }

    @Test
    public void testGetVehicle()  {

        VehicleController controller = new VehicleController();
        String result = controller.getVehicle("nonexistent-id");


        assertNull(result);
    }
}



