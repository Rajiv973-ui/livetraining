package com.example.demo.controllers;

import org.reactivestreams.Publisher;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RestController
public class ClientController {

	
	
	private WebClient client;

	public ClientController(WebClient client) {
		super();
		this.client = client;
	}
	
	
	@GetMapping(path = "/client")
	public Flux<String> getAccountInfo() {
		
		
		return client.get().uri("lb://ACCOUNT-SERVICE/accounts")
				           .retrieve().bodyToFlux(String.class);
	}
	
}
