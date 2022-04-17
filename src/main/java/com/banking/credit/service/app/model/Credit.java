package com.banking.credit.service.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.banking.credit.service.app.entity.Customer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credits")
public class Credit {
	
	@Id
	private String id;
	
	@Transient
	private Customer customer;
	
	private String requestId;
	
	private int status;
	
	private Boolean fullyPaid = false;
	
	private Double totalLoan;
	
	private Double totalPaid;
	
	private Boolean activeLoan = false;
	
	private Boolean claimedLoan = false;
	
	private Double interestRate = 6.90;
	
	private int actualQuota;
	
	private int totalQuotas;
	
	private int amountPerQuota;
	
	private List<Payment> payments;
	
	private Double nextQuotaAmount;
	
	private Date fullyPaymentDate;
	
	private Date lastPaymentDate;
	
	private Date nextPaymentDate;
	
	private Boolean isOnDate;
	
	private Double outOfDateInterest = 4.00;
	
	private Date lastReview;
	
	private Date createAt;
}
