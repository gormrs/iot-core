package com.my.api.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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



