package com.example.loadbalancer.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoundRobinService {
	
	 private final List<String> instances = List.of(
	            "http://localhost:8081",
	            "http://localhost:8082"
	    );

	    private final AtomicInteger counter = new AtomicInteger(0);

	    public String getNextInstance() {
	        int index = counter.getAndIncrement() % instances.size();
	        return instances.get(index);
	    }

}
