package com.banking.credit.service.app.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

	private String id;
	private Boolean debit = false;
	private String accountId;
	private List<AccountReduced> allAccounts;
	private String customerId;
	private Integer ccv;
	private Long cardNumber;
	private Double baseCreditLine;
	private Double remainingCreditLine;
	private LocalDate expiration;
	private LocalDate createAt;
	
	
}
