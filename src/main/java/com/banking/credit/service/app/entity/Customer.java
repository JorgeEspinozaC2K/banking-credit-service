package com.banking.credit.service.app.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Customer {

	private String id;
	private String name;
	private String lastName;
	private Integer personalIdentifier;
	private Integer tributaryIdentifier;
	private Boolean isTributary;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
}
