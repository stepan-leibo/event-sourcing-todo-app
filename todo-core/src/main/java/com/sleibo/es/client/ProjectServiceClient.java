package com.sleibo.es.client;

import com.sleibo.es.domain.Project;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProjectServiceClient {
    private final RestTemplate restTemplate;

    public ProjectServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Project> getProjects() {
        return restTemplate.exchange(
            "http://localhost:6903/projects",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Project>>() {
            }).getBody();
    }
}
