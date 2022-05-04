package com.banking.credit.service.app.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	private Boolean forCard = false;
	
	private Long cardNumber;
	
	private String customerId;
	
	private Boolean fullyPaid = false;
	
	private Double totalLoan;
	
	private Double totalPaid = 0.0;
	
	private Double remainingLoan = totalLoan;
	
	private Boolean activeLoan = false;
	
	private Boolean claimedLoan = false;
	
	private Double interestRate = 6.90;
	
	private Double met = (Math.pow((1 + (interestRate / 100)), (30 / 360)) - 1);
	
	private Double interest = met * (totalLoan - totalPaid);
	
	private Integer totalQuotas;
	
	private Double crf = ((met / 100)
			* Math.pow((1 + (met / 100)), totalQuotas))
			/ Math.pow((1 + (met / 100)), totalQuotas) - 1;
	
	private Integer actualQuota = 1;
	
	private Integer remainingQuotas = totalQuotas;
	
	private Double nextQuotaAmount = totalLoan * crf;
    
	private Double nextMinPaymentAmount = crf-interest;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate quotaNextPaymentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate quotaLastPaymentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fullyPaymentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate updateAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createAt = LocalDate.now();
}
