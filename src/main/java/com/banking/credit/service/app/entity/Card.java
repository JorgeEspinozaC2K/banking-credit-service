package com.banking.credit.service.app.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Card {

	private String id;
	private String customerId;
	private Integer ccv;
	private Long cardNumber;
	private Date expiration;
	private Date createAt;
	
	
}
