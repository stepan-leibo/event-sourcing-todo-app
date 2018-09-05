package com.sleibo.es.event.service;

import com.mongodb.client.MongoCursor;
import org.axonframework.mongo.MongoTemplate;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoEventStoreService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> getAllDomainEntries() {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> iterator = mongoTemplate.eventCollection().find().limit(100).iterator();
        while (iterator.hasNext()) {
            documents.add(iterator.next());
        }

        return documents;
    }


}
