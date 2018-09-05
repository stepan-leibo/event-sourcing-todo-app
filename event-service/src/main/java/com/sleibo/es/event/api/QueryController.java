package com.sleibo.es.event.api;

import com.sleibo.es.event.service.MongoEventStoreService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryController {

    @Autowired
    private MongoEventStoreService mongoService;

    @GetMapping("/events")
    public ResponseEntity<List<Document>> getEvents() {
        return ResponseEntity.ok(mongoService.getAllDomainEntries());
    }
}
