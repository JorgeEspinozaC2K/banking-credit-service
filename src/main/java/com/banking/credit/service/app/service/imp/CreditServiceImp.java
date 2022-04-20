package com.banking.credit.service.app.service.imp;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.service.app.entity.Customer;
import com.banking.credit.service.app.model.Credit;
import com.banking.credit.service.app.model.Payment;
import com.banking.credit.service.app.repository.CreditRepository;
import com.banking.credit.service.app.repository.PaymentRepository;
import com.banking.credit.service.app.service.CreditService;
import com.banking.credit.service.app.webclient.CreditWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImp implements CreditService {

	private static final Logger log = LoggerFactory.getLogger(CreditServiceImp.class);

	private CreditWebClient creditWebclient = new CreditWebClient();

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
		return creditRepository.findByForCard(forCard).defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null
						? Mono.error(new InterruptedException(
								"Response from CREDITS SERVICE returns empty, records not found or doesn't exist"))
						: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByCustomerById(String customerId) {
		return creditRepository.findByCustomerId(customerId).defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null ? Mono.error(new InterruptedException(
						"Response from CREDITS SERVICE empty, because customer ID: " + customerId + "doesn't exist"))
						: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByActiveLoan(Boolean activeLoan) {
		return creditRepository.findByActiveLoan(activeLoan).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(
										new InterruptedException("Response from CREDITS SERVICE empty, maybe because "
												+ (activeLoan ? "ACTIVE" : "INACTIVE") + " loans doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByIsOnDate(Boolean isOnDate) {
		return creditRepository.findByIsOnDate(isOnDate).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ (isOnDate ? "" : "DEBTOR'S") + " credits doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByTotalLoanGreaterThanEqual(Integer totalLoan) {
		return creditRepository.findByTotalLoanGreaterThanEqual(Double.valueOf(totalLoan)).defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null
						? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+ "credits [GREATER THAN EQUAL: " + totalLoan + "] doesn't exist."))
						: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByTotalLoanBetween(Integer minTotalLoan, Integer maxTotalLoan) {
		return creditRepository.findByTotalLoanBetween(Double.valueOf(minTotalLoan), Double.valueOf(maxTotalLoan))
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null
						? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
								+ "credits [BETWEEN: " + minTotalLoan + " AND " + maxTotalLoan + "] doesn't exist."))
						: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByTotalLoanLessThan(Integer totalLoan) {
		return creditRepository.findByTotalLoanLessThan(Double.valueOf(totalLoan)).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ "credits [LESS THAN: " + totalLoan + "] doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByForCardAndCustomerById(Boolean forCard, String customerId) {
		return creditRepository.findByForCard(forCard).filter(c -> c.getCustomerId() == customerId)
				.defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null
						? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty,maybe because "
								+ "credits " + (forCard ? "[LINKED]" : "[UNLINKED]")
								+ " to a card and with CUSTOMER ID: [" + customerId + "] doesn't match with no one."))
						: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});

	}

	@Override
	public Flux<Credit> findByCreateAt(Date createAt) {
		return creditRepository.findByCreateAt(createAt).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ "credits created [AT: " + createAt + "] doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByCreateAtBefore(Date createAt) {
		return creditRepository.findByCreateAtBefore(createAt).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ "credits created [BEFORE: " + createAt + "] doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Flux<Credit> findByCreateAtAfter(Date createAt) {
		return creditRepository.findByCreateAtAfter(createAt).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ "credits created [AFTER: " + createAt + "] doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> findByRequestId(String requestId) {
		return creditRepository.findByRequestId(requestId).defaultIfEmpty(new Credit())
				.flatMap(
						_credit -> _credit.getId() == null
								? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
										+ "credits with [REQUEST ID: " + requestId + "] doesn't exist."))
								: Mono.just(_credit))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> findById(String id) {
		return creditRepository.findById(id).defaultIfEmpty(new Credit()).flatMap(_credit -> _credit.getId() == null
				? Mono.error(new InterruptedException("Response from CREDITS SERVICE empty, because "
						+ "credits with [ID: " + id + "] doesn't exist."))
				: Mono.just(_credit)).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Credit> save(Credit credit) {

		if (credit.getId() != null) {
			return creditRepository.findById(credit.getId()).defaultIfEmpty(new Credit()).flatMap(_credit -> {
				if (_credit.getId() == null) {
					return Mono.error(new InterruptedException("Can't update this credit: Does not exist"));
				} else {
					credit.setTotalLoan(_credit.getTotalLoan());
					credit.setRemainingLoan(_credit.getRemainingLoan() - credit.getTotalPaid());
					credit.setNextQuotaAmount(credit.getRemainingLoan() * credit.getCrf());
					credit.setRequestId(_credit.getRequestId());
					credit.setCreateAt(_credit.getCreateAt());
					credit.setCustomerId(_credit.getCustomerId());
					credit.setForCard(_credit.getForCard());
					credit.setUpdateAt(new Date());
					return creditRepository.save(credit);
				}
			});

		} else {
			return creditWebclient.findCustomer(credit.getCustomerId()).defaultIfEmpty(new Customer()).flatMap(_cus -> {
				if (_cus.getId() == null) {
					return Mono
							.error(new InterruptedException("CUSTOMER NOT FOUND OR ERROR INTO THE CUSTOMER SERVICE"));
				} else {
					if (!_cus.getIsTributary()) {
						return creditRepository.findByForCard(false).defaultIfEmpty(new Credit()).flatMap(_data -> {
							if (_data.getId() == null) {
								return Mono.error(new InterruptedException("Customer ID: " + _data));
							} else {
								return Flux.just(_data);
							}
						}).filter(_total -> _total.getCustomerId() == credit.getCustomerId()).count().flatMap(c -> {
							if (c > 0) {
								return Mono.error(new InterruptedException(
										"Personal customer cant have " + "more than ONE CREDIT"));
							} else {
								credit.setTotalPaid(0.00);
								credit.setRemainingLoan(credit.getTotalLoan());
								credit.setMet((Math.pow((1 + (credit.getInterestRate() / 100)), (30 / 360)) - 1));
								credit.setInterest(credit.getMet() * (credit.getTotalLoan() - credit.getTotalPaid()));
								credit.setCrf(((credit.getMet() / 100)
										* Math.pow((1 + (credit.getMet() / 100)), credit.getTotalQuotas()))
										/ Math.pow((1 + (credit.getMet() / 100)), credit.getTotalQuotas()) - 1);
								credit.setNextQuotaAmount(credit.getTotalLoan() * credit.getCrf());
								credit.setNextMinPaymentAmount(credit.getCrf() - credit.getInterest());
								credit.setRemainingQuotas(credit.getTotalQuotas());
								credit.setActualQuota(1);
								credit.setUpdateAt(new Date());
								credit.setCreateAt(new Date());
								return creditRepository.save(credit);
							}
						});
					} else {
						return creditRepository.save(credit);
					}
				}
			});
		}
	}

	@Override
	public Mono<Void> delete(Credit credit) {
		return creditRepository.delete(credit).onErrorResume(_ex -> {
			log.error(_ex.getMessage());
			return Mono.empty();
		});
	}

	// Payments section

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
