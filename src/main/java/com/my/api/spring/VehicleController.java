package com.my.api.spring;


import com.fasterxml.jackson.databind.JsonNode;
import com.my.api.spring.document.DocumentStoreHolder;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @GetMapping("/{id}")
    public String getVehicle(@PathVariable String id) {
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            JsonNode root = session.load(JsonNode.class, id);
            if (root != null) {
                return root.toString();
            }
        }

        return "Vehicle with id: " + id;
    }

    @GetMapping("/ping")
    public String pingPong() {
        System.out.println("ping");
        return "pong";
    }

    @GetMapping("/{longitude}/{latitude}")
    public String getVehiclesInRadius(@PathVariable double longitude, @PathVariable double latitude) {
        try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
            return session.advanced().rawQuery(JsonNode.class, "from * where spatial.within(Coordinates, spatial.circle($longitude, $latitude, 1000))")
                    .addParameter("longitude", longitude)
                    .addParameter("latitude", latitude)
                    .toList().toString();
        }
    }


}
