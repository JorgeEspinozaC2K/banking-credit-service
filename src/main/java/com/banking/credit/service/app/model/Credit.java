package com.banking.credit.service.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class Credit {
	
	private String id;
	
	private Boolean forCard = false;
	
	private String cardNumber;
	
	private String customerId;
	
	private String requestId;
	
	private Boolean fullyPaid = false;
	
	private Double totalLoan;
	
	private Double totalPaid;
	
	private Double remainingLoan;
	
	private Boolean activeLoan = false;
	
	private Boolean claimedLoan = false;
	
	private Double interestRate = 6.90;
	
	private Double interest;
	
	private Double met;
	
	private Double crf;
	
	private Integer actualQuota;
	
	private Integer totalQuotas;
	
	private Integer remainingQuotas;
	
	private Double nextQuotaAmount;
    
	private Double nextMinPaymentAmount;
	
	private Date fullyPaymentDate;
	
	private Date updateAt;
	
	private Date createAt;
}
