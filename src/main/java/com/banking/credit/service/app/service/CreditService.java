package com.banking.credit.service.app.service;


import java.time.LocalDate;

import com.banking.credit.service.app.entity.Card;
import com.banking.credit.service.app.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService{
	
	public Flux<Credit> findAll();
	
	public Flux<Credit> findAllTenLast(Long cardNumber);
	
	public Flux<Credit> findByForCard(Boolean forCard);
	
	public Flux<Credit> findByCardNumber(String cardNumber);
	
	public Flux<Credit> findByCustomerById(String customerId);
	
	public Flux<Credit> findByActiveLoan(Boolean activeLoan);
	
	public Flux<Credit> findByForCardAndCustomerById(Boolean forCard,String customerId);
	
	public Flux<Credit> findByTotalLoanGreaterThanEqual(Integer totalLoan);
	
	public Flux<Credit> findByTotalLoanBetween(Integer minTotalLoan, Integer maxTotalLoan);
	
	public Flux<Credit> findByTotalLoanLessThan(Integer totalLoan);
	
	public Flux<Credit> findByCreateAt(LocalDate createAt);
	
	public Flux<Credit> findByCreateAtBefore(LocalDate createAt);
	
	public Flux<Credit> findByCreateAtAfter(LocalDate createAt);
	
	public Flux<Credit> findByCreateAtBetween(LocalDate createAtF, LocalDate createAtL);
	
	public Mono<Credit> findById(String id);
	
	public Mono<Credit> save(Credit credit);
	
	public Mono<Void> delete(Credit credit);
	
	public Mono<Card> newCreditCard(String customerId, Double creditLine);

}
