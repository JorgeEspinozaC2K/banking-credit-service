package com.banking.credit.service.app.webclient;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.credit.service.app.entity.Card;
import com.banking.credit.service.app.entity.Customer;

import reactor.core.publisher.Mono;

@Service
public class CreditWebClient {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
	
	public Mono<Customer> findCustomer(String id){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/customer/{id}",id)
				.retrieve()
				.bodyToMono(Customer.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
	public Mono<Card> createCard(Long cardNumber, String customerId, Double creditLine){
		
		Card _card = new Card();
		_card.setBaseCreditLine(creditLine);
		_card.setCustomerId(customerId);
		_card.setCardNumber(cardNumber);
		_card.setCcv(Integer.parseInt(String.format("%3d", new Random().nextInt(99999))));
		_card.setExpiration(LocalDate.now());
		_card.setCreateAt(LocalDate.now());
		return WebClient
				.create("http://localhost:8080")
				.post()
				.uri("/card/new")
				.body(Mono.just(_card),Card.class)
				.retrieve()
				.bodyToMono(Card.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
	public Mono<Card> updateCustomerCards(Card card) {
		return WebClient
				.create("http://localhost:8080")
				.post()
				.uri("/card/update")
				.body(Mono.just(card),Card.class)
				.retrieve()
				.bodyToMono(Card.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
	public Mono<Card> findCard(Long cardNumber) {
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/card/{id}", cardNumber)
				.retrieve()
				.bodyToMono(Card.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
}
