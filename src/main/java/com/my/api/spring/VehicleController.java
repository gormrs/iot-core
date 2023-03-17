package com.my.api.spring;


import com.my.api.spring.document.DocumentStoreHolder;
import com.my.api.spring.mqtt.VehiclePosition;
import net.ravendb.client.documents.queries.spatial.PointField;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {



    @GetMapping("/{id}")
    public String getVehicle(@PathVariable String id) {
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            VehiclePosition root = session.load(VehiclePosition.class, id);
            if (root != null) {
                return root.toString();
            }
        }

        return null;
    }

    @GetMapping("/ping")
    public String pingPong() {
        // for testing purposes
        System.out.println("ping");
        return "pong";
    }

    @GetMapping("/{latitude}/{longitude}")
    public String getVehiclesInRadius(@PathVariable double latitude, @PathVariable double longitude) {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        List<String> closestVehicles = new ArrayList<>();
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            List<VehiclePosition> results = session
                    .query(VehiclePosition.class)
                    .spatial(new PointField("lat", "long"), // checks if the vehicle is within the radius
                            criteria -> criteria.withinRadius(1, latitude, longitude)) // 1km radius
                    .toList();


            for (VehiclePosition result : results) {
                double distance = distanceCalculator.distance(latitude, longitude, result.getLat(), result.getLongitude());

                closestVehicles.add(String.format("Vehicle ID: %s, Distance: %.2f km, Next Stop: %s, Timestamp: %d\n",
                        result.getId(), distance, result.getNextStop(), result.getTsi()));

            }

            if (closestVehicles.isEmpty()) {
                return "No vehicles found in radius";
            }

            return closestVehicles.toString(); // returns as string for easy reading, should eventually return as json




        }
    }
}






