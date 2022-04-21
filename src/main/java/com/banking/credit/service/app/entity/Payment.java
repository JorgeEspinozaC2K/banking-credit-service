package com.banking.credit.service.app.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Payment {
	
	private String id;
	
	private String creditId;
	
	private Date paymentDate;
	
	private Integer quota;
	
	private Double quotaAmount;
	
	private Double minPaymentAmount;
	
	private Double paidAmount;
	
	private Double interestAmount;
	
	private Double totalAmount;
	
	private Double remainingAmount;
	
	private Integer remainingQuotas;
	
}
