package com.banking.credit.service.app.service.imp;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.service.app.model.Credit;
import com.banking.credit.service.app.model.Payment;
import com.banking.credit.service.app.repository.CreditRepository;
import com.banking.credit.service.app.repository.PaymentRepository;
import com.banking.credit.service.app.service.CreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImp implements CreditService{
	
	private static final Logger log = LoggerFactory.getLogger(CreditServiceImp.class);

	@Autowired
	private CreditRepository creditRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Flux<Credit> findAll() {
		return creditRepository.findAll();
	}

	@Override
	public Flux<Credit> findByForCard(Boolean forCard) {
		return creditRepository.findByForCard(forCard)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE returns empty, records not found or doesn't exist")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByCustomerById(String customerId) {
		return creditRepository.findByCustomerId(customerId)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because customer ID: " + customerId + "doesn't exist")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByActiveLoan(Boolean activeLoan) {
		return creditRepository.findByActiveLoan(activeLoan)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, maybe because "
								+ (activeLoan ? "ACTIVE":"INACTIVE") +" loans doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByIsOnDate(Boolean isOnDate) {
		return creditRepository.findByIsOnDate(isOnDate)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+ (isOnDate ? "":"DEBTOR'S") +" credits doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByTotalLoanGreaterThanEqual(Integer totalLoan) {
		return creditRepository.findByTotalLoanGreaterThanEqual(Double.valueOf(totalLoan))
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits [GREATER THAN EQUAL: " + totalLoan + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	@Override
	public Flux<Credit> findByTotalLoanBetween(Integer minTotalLoan, Integer maxTotalLoan) {
		return creditRepository
				.findByTotalLoanBetween(Double.valueOf(minTotalLoan) , Double.valueOf(maxTotalLoan) )
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits [BETWEEN: " + minTotalLoan + " AND " + maxTotalLoan + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	@Override
	public Flux<Credit> findByTotalLoanLessThan(Integer totalLoan) {
		return creditRepository.findByTotalLoanLessThan(Double.valueOf(totalLoan))
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits [LESS THAN: " + totalLoan + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	@Override
	public Flux<Credit> findByForCardAndCustomerById(Boolean forCard, String customerId) {
		return creditRepository.findByForCard(forCard)
				.filter(c-> c.getCustomer().getId() == customerId )
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty,maybe because "
								+ "credits " + (forCard ? "[LINKED]" : "[UNLINKED]") + " to a card and with CUSTOMER ID: [" + customerId + "] doesn't match with no one.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
		
	}

	@Override
	public Flux<Credit> findByCreateAt(Date createAt) {
		return creditRepository.findByCreateAt(createAt)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits created [AT: " + createAt + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	@Override
	public Flux<Credit> findByCreateAtBefore(Date createAt) {
		return creditRepository.findByCreateAtBefore(createAt)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits created [BEFORE: " + createAt + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	@Override
	public Flux<Credit> findByCreateAtAfter(Date createAt) {
		return creditRepository.findByCreateAtAfter(createAt)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits created [AFTER: " + createAt + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> findByRequestId(String requestId) {
		return creditRepository.findByRequestId(requestId)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits with [REQUEST ID: " + requestId + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> findById(String id) {
		return creditRepository.findById(id)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ?
						Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+"credits with [ID: " + id + "] doesn't exist.")):
						Mono.just(_credit)
				)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> save(Credit credit) {
		return creditRepository.save(credit)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Void> delete(Credit credit) {
		return creditRepository.delete(credit)
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
	//Payments section

	@Override
	public Flux<Payment> findAllPyments() {
		return paymentRepository.findAll();
	}

	@Override
	public Flux<Payment> findPaymentByCreditId(String creditId) {
		return paymentRepository.findByCreditId(creditId);
	}

	@Override
	public Mono<Payment> findPaymentById(String id) {
		return paymentRepository.findById(id);
	}

	@Override
	public Mono<Payment> savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Mono<Void> deletePayment(Payment payment) {
		return paymentRepository.delete(payment);
	}


}
