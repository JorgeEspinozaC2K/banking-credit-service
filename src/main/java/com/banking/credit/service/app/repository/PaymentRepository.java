package com.banking.credit.service.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.credit.service.app.model.Payment;

import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
	
	/**
	 * 
	 * @param creditId
	 * @return
	 */
	public Flux<Payment> findByCreditId(String creditId);
	
}
