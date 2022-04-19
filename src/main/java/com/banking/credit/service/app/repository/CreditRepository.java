package com.banking.credit.service.app.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.credit.service.app.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository extends ReactiveMongoRepository<Credit,String>{
	
	/**
	 * 
	 * @param forCard
	 * @return
	 */
	public Flux<Credit> findByForCard(Boolean forCard);
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public Flux<Credit> findByCustomerId(String customerId);
	
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	public Mono<Credit> findByRequestId(String requestId);
	
	/**
	 * 
	 * @param activeLoan
	 * @return
	 */
	public Flux<Credit> findByActiveLoan(Boolean activeLoan);
	
	/**
	 * 
	 * @param isOnDate
	 * @return
	 */
	public Flux<Credit> findByIsOnDate(Boolean isOnDate);
	
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
	public Flux<Credit> findByCreateAt(Date createAt);
	
	public Flux<Credit> findByCreateAtBefore(Date createAt);
	
	public Flux<Credit> findByCreateAtAfter(Date createAt);
	
	/**
	 * 
	 * @param remainingQuotas
	 * @return
	 */
	public Flux<Credit> findByRemainingQuotas(Integer remainingQuotas);
	
	
}
