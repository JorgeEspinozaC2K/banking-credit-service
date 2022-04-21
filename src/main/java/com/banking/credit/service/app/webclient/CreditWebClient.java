package com.banking.credit.service.app.webclient;

import java.util.Date;
import java.util.Random;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.banking.credit.service.app.entity.Card;
import com.banking.credit.service.app.entity.Customer;

import reactor.core.publisher.Mono;;

public class CreditWebClient {
	
	private Builder creditWebClient = WebClient.builder();
	
	public Mono<Customer> findCustomer(String id){
		return creditWebClient.build()
				.get()
				.uri("http://localhost:8082/api/v1/customer/{id}",id)
				.retrieve()
				.bodyToMono(Customer.class);
	}
	
	public Mono<Card> createCard(Long cardNumber, String customerId){
		
		Card _card = new Card();
		_card.setCustomerId(customerId);
		_card.setCardNumber(cardNumber);
		_card.setCcv(Integer.parseInt(String.format("%3d", new Random().nextInt(99999))));
		_card.setExpiration(new Date());
		_card.setCreateAt(new Date());
		return creditWebClient.build()
				.post()
				.uri("http://localhost:8085/api/v1/card/new",_card)
				.retrieve()
				.bodyToMono(Card.class);
	}
	
}
