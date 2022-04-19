package com.banking.credit.service.app.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.banking.credit.service.app.entity.Customer;

import reactor.core.publisher.Mono;;

public class CreditWebClient {
	
	private Builder creditWebClient = WebClient.builder();
	
	public Mono<Customer> findCustomer(String id){
		return creditWebClient.build()
				.get()
				.uri("http://localhost:8082/{id}",id)
				.retrieve()
				.bodyToMono(Customer.class);
	}
	
}
