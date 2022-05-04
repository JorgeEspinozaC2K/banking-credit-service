package com.banking.credit.service.app.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.credit.service.app.model.Credit;

import reactor.core.publisher.Flux;

public interface CreditRepository extends ReactiveMongoRepository<Credit,String>{
	
	/**
	 * 
	 * @param forCard
	 * @return
	 */
	public Flux<Credit> findByForCard(Boolean forCard);
	
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	public Flux<Credit> findTop10ByCardNumberOrderByCreateAtDesc(Long cardNumber);
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public Flux<Credit> findByCustomerId(String customerId);
	
	/**
	 * 
	 * @param activeLoan
	 * @return
	 */
	public Flux<Credit> findByActiveLoan(Boolean activeLoan);
	
	/**
	 * 
	 * @param totalLoan
	 * @return
	 */
	public Flux<Credit> findByTotalLoanGreaterThanEqual(Double totalLoan);
	
	public Flux<Credit> findByTotalLoanBetween(Double minTotalLoan, Double maxTotalLoan);
	
	public Flux<Credit> findByTotalLoanLessThan(Double totalLoan);	
	
	/**
	 * 
	 * @param createAt
	 * @return
	 */
	public Flux<Credit> findByCreateAt(LocalDate createAt);
	
	public Flux<Credit> findByCardNumber(String cardNumber);
	
	public Flux<Credit> findByCreateAtBefore(LocalDate createAt);
	
	public Flux<Credit> findByCreateAtAfter(LocalDate createAt);
	
	/**
	 * 
	 * @param remainingQuotas
	 * @return
	 */
	public Flux<Credit> findByRemainingQuotas(Integer remainingQuotas);
	
	
}
