package com.example.loadbalancer.controller;

import com.example.loadbalancer.service.RoundRobinService;
import com.example.loadbalancer.exception.ServiceUnavailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class LoadBalancerController {

    private final RoundRobinService roundRobinService;
    private final RestTemplate restTemplate = new RestTemplate();

    public LoadBalancerController(RoundRobinService roundRobinService) {
        this.roundRobinService = roundRobinService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> forwardRequest() {

        String targetUrl = roundRobinService.getNextInstance() + "/api/hello";

        try {
            return restTemplate.getForEntity(targetUrl, String.class);
        } catch (Exception ex) {
            throw new ServiceUnavailableException("Target service is down: " + targetUrl);
        }
    }
}