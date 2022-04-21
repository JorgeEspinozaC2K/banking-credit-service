package com.banking.credit.service.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.credit.service.app.model.Credit;
import com.banking.credit.service.app.service.CreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/credit")
public class CreditController {
	
	@Autowired
	private CreditService creditService;
	
	@GetMapping
	public Flux<Credit> creditIndex(){
		return creditService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Credit> searchCreditId(@PathVariable String id){
		return creditService.findById(id);
	}
	
	@GetMapping("/customer/{customerId}")
	public Flux<Credit> searchCreditByCustomerId(@PathVariable String customerId){
		return creditService.findByCustomerById(customerId);
	}
	
	@GetMapping("/ifc/{forCard}")
	public Flux<Credit> searchCreditByForCard(@PathVariable Boolean forCard){
		return creditService.findByForCard(forCard);
	}
	
	@GetMapping("/ifc/{forCard}/{customerId}")
	public Flux<Credit> searchCreditByForCardAndCustomerId(@PathVariable Boolean forCard,@PathVariable String customerId){
		return creditService.findByForCardAndCustomerById(forCard, customerId);
	}
	
	@GetMapping("/loang/{loanAmount}")
	public Flux<Credit> searchCreditByGreaterAmountThan(@PathVariable Integer loanAmount){
		return creditService.findByTotalLoanGreaterThanEqual(loanAmount);
	}
	
	@GetMapping("/loanl/{loanAmount}")
	public Flux<Credit> searchCreditByLessAmountThan(@PathVariable Integer loanAmount){
		return creditService.findByTotalLoanLessThan(loanAmount);
	}
	
	@GetMapping("/loan/{minLoanAmount}/{maxLoanAmount}")
	public Flux<Credit> searchCreditByAmountBetween(@PathVariable Integer minLoanAmount,@PathVariable Integer maxLoanAmount){
		return creditService.findByTotalLoanBetween(minLoanAmount, maxLoanAmount);
	}
	
	@GetMapping("/created/at/{date}")
	public Flux<Credit> searchCreditByCreationDate(@PathVariable Date date){
		return creditService.findByCreateAt(date);
	}
	
	@GetMapping("/created/after/{date}")
	public Flux<Credit> searchCreditByCreationDateAfter(@PathVariable Date date){
		return creditService.findByCreateAtAfter(date);
	}
	
	@GetMapping("/created/before/{date}")
	public Flux<Credit> searchCreditByCreationDateBefore(@PathVariable Date date){
		return creditService.findByCreateAtBefore(date);
	}


	@GetMapping("/active")
	public Flux<Credit> searchCreditByIsActive(@PathVariable Boolean active){
		return creditService.findByActiveLoan(true);
	}
	
	@PostMapping("/save")
	public Mono<Credit> saveCredit(@RequestBody Credit credit){
		return creditService.save(credit);
	}
	
	@DeleteMapping("/delete")
	public Mono<Void> deleteCredit(@RequestBody Credit credit){
		return creditService.delete(credit);
	}
}
