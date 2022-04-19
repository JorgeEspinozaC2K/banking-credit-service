package com.banking.credit.service.app.service;


import java.util.Date;

import com.banking.credit.service.app.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService extends PaymentService{
	
	public Flux<Credit> findAll();
	
	public Flux<Credit> findByForCard(Boolean forCard);
	
	public Flux<Credit> findByCustomerById(String customerId);
	
	public Flux<Credit> findByActiveLoan(Boolean activeLoan);
	
	public Flux<Credit> findByIsOnDate(Boolean isOnDate);
	
	public Flux<Credit> findByForCardAndCustomerById(Boolean forCard,String customerId);
	
	public Flux<Credit> findByTotalLoanGreaterThanEqual(Integer totalLoan);
	
	public Flux<Credit> findByTotalLoanBetween(Integer minTotalLoan, Integer maxTotalLoan);
	
	public Flux<Credit> findByTotalLoanLessThan(Integer totalLoan);
	
	public Flux<Credit> findByCreateAt(Date createAt);
	
	public Flux<Credit> findByCreateAtBefore(Date createAt);
	
	public Flux<Credit> findByCreateAtAfter(Date createAt);
	
	public Mono<Credit> findByRequestId(String requestId);
	
	public Mono<Credit> findById(String id);
	
	public Mono<Credit> save(Credit credit);
	
	public Mono<Void> delete(Credit credit);

}
