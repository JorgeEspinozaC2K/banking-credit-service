package com.banking.credit.service.app.service.imp;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.service.app.entity.Card;
import com.banking.credit.service.app.model.Credit;
import com.banking.credit.service.app.repository.CreditRepository;
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
	public Flux<Credit> findByCreateAt(LocalDate createAt) {
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
	public Flux<Credit> findByCreateAtBefore(LocalDate createAt) {
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
	public Flux<Credit> findByCreateAtAfter(LocalDate createAt) {
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
	public Mono<Credit> findById(String id) {
		return creditRepository.findById(id).defaultIfEmpty(new Credit())
				.flatMap(_credit -> _credit.getId() == null
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
			return creditRepository.findById(credit.getId())
					.switchIfEmpty(Mono.error(new InterruptedException("Can't update this credit: Does not exist")))
					.map(_credit ->{
						credit.setCardNumber(_credit.getCardNumber());
						credit.setForCard(_credit.getForCard());
						credit.setTotalLoan(_credit.getTotalLoan());
						credit.setTotalPaid(_credit.getTotalPaid() + credit.getTotalPaid());
						credit.setRemainingLoan(_credit.getRemainingLoan() - credit.getTotalPaid());
						credit.setInterest(_credit.getMet() * credit.getRemainingLoan());
						credit.setNextQuotaAmount(credit.getRemainingLoan() * credit.getCrf());
						credit.setNextMinPaymentAmount(credit.getRemainingLoan()-credit.getInterest());
						credit.setCreateAt(_credit.getCreateAt());
						credit.setCustomerId(_credit.getCustomerId());
						credit.setForCard(_credit.getForCard());
						credit.setUpdateAt(LocalDate.now());
						credit.setCreateAt(_credit.getCreateAt());
						return credit;
					})
					.flatMap(cred -> creditRepository.save(cred))
					.onErrorResume(_ex ->{
						log.error(_ex.getMessage());
						return Mono.empty();
					});

		} else {
			return creditWebclient.findCustomer(credit.getCustomerId())
					.switchIfEmpty(Mono.error(new InterruptedException("Can't create this credit: Customer does not exist")))
					.flatMap(_cus -> {
					return creditRepository.findByCustomerId(credit.getId())
							.filter(c->c.getQuotaLastPaymentDate().isAfter(LocalDate.now()))
							.count()
							.flatMap(count-> {
								if(count > 0) {
									return Mono.error(new InterruptedException("This customer have unpaid credits"));
								}else {
									if (!_cus.getIsTributary() && !credit.getForCard()) {
										return creditRepository.findByForCard(false)
												.switchIfEmpty(Mono.error(new InterruptedException("Credits for cards does not exist")))
												.flatMap(_data ->Mono.just(_data))
												.filter(_total -> _total.getCustomerId() == credit.getCustomerId())
												.count()
												.flatMap(c -> c > 0 ? Mono.error(new InterruptedException(
														"Personal customer cant have more than ONE CREDIT")):
											  			 creditRepository.save(credit));
									} else {
										if (credit.getForCard() && credit.getCardNumber() != null) {
											creditWebclient.findCard(credit.getCardNumber())
													.filter(crd -> crd.getCustomerId() == credit.getCustomerId() && !crd.getDebit())
													.switchIfEmpty(Mono.error(new InterruptedException("Forbiden, customer doesn't match")))
													.filter(crd -> crd.getRemainingCreditLine() > credit.getTotalLoan())
													.map(credCard->{
														credCard.setRemainingCreditLine(credCard.getRemainingCreditLine() - credit.getTotalLoan());
														return credCard;
													}).flatMap(newCred ->{
														creditWebclient.updateCustomerCards(newCred);
														return creditRepository.save(credit);
													});
										}
										credit.setCardNumber(null);
										credit.setForCard(false);
										return creditRepository.save(credit);
									}
								}
							});
			}).onErrorResume(_ex ->{
				log.error(_ex.getMessage());
				return Mono.empty();
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

	@Override
	public Flux<Credit> findByCardNumber(String cardNumber) {
		return creditRepository.findByCardNumber(cardNumber)
				.defaultIfEmpty(new Credit())
				.flatMap(_cbc ->
					_cbc.getId() == null ?
							Mono.error(new InterruptedException("Not found")):
								Flux.just(_cbc))
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Flux.empty();
				});
	}

	@Override
	public Flux<Credit> findByCreateAtBetween(LocalDate createAtF, LocalDate createAtL) {
		return creditRepository.findByCreateAtAfter(createAtF)
				.filter(c-> c.getCreateAt().isBefore(createAtL==null? LocalDate.now(): createAtL));
	}

	@Override
	public Mono<Card> newCreditCard(String customerId, Double creditLine) {
		return creditWebclient.createCard(cardNumberCreation(0L), customerId, creditLine);
	}
	
	public Long cardNumberCreation(Long cardNumber) {
		Long crn = Long.parseLong(String.format("%16d",ThreadLocalRandom.current().nextLong(9999999999999999L)));
		if (cardNumber == 0L) {
			return cardNumberCreation(Math.abs(crn));
		}else {
			Long cn = creditWebclient.findCard(cardNumber)
					.defaultIfEmpty(new Card())
					.block().getCardNumber();
			
			return cn == null ? cn : cardNumberCreation(Math.abs(crn));
		}
	}
	
	/**
	 * Metodo para encontrar los 10 ultimos movimientos de la tarjeta "X"
	 */
	@Override
	public Flux<Credit> findAllTenLast(Long cardNumber) {
		return creditRepository.findTop10ByCardNumberOrderByCreateAtDesc(cardNumber);
	}

}
