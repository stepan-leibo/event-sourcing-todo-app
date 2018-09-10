package com.sleibo.es.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsServiceClient {
    private final RestTemplate restTemplate;

    public StatsServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public Map<Long, AtomicInteger> getStats() {
        return restTemplate.exchange(
            "http://localhost:6904/stats",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Map<Long, AtomicInteger>>() {}).getBody();
    }
}
