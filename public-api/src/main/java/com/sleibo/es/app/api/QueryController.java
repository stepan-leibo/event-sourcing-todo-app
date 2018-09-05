package com.sleibo.es.app.api;

import com.sleibo.es.client.EventServiceClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class QueryController {

    private final EventServiceClient eventServiceClient;

    @Autowired
    public QueryController() {
        eventServiceClient = new EventServiceClient();
    }

    @GetMapping("/events")
    public ResponseEntity<List<Document>> getEvents() {
        return ResponseEntity.ok(eventServiceClient.getEvents());
    }
}
