package com.banking.credit.service.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {
	
	@Id
	private String id;
	
	private int personalIdentifier;
	
	private Date paymentDate;
	
	private Boolean atTime;
	
	private int quota;
	
	private Double quotaAmount;
	
	private Double minPaymentAmount;
	
	private Double paidAmount;
	
	private Double interestAmount;
	
	private Double outOfDateAmount;
	
	private Double totalAmount;
	
	private Double remainingAmount;
	
	private int remainingQuotas;
	
}
