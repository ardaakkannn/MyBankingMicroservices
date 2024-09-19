package com.ardakkan.demo.dtos;

import java.time.LocalDateTime;

public class TransactionDto {

    private Long id;
    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private String transactionType; // "TRANSFER", "WITHDRAWAL", "DEPOSIT"
    private String currency;
    private LocalDateTime transactionDate;
	
    
    
    
    public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}




	public TransactionDto(Long id, Long senderAccountId, Long receiverAccountId, Double amount, String transactionType,
			String currency, LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.senderAccountId = senderAccountId;
		this.receiverAccountId = receiverAccountId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.currency = currency;
		this.transactionDate = transactionDate;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Long getSenderAccountId() {
		return senderAccountId;
	}




	public void setSenderAccountId(Long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}




	public Long getReceiverAccountId() {
		return receiverAccountId;
	}




	public void setReceiverAccountId(Long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}




	public Double getAmount() {
		return amount;
	}




	public void setAmount(Double amount) {
		this.amount = amount;
	}




	public String getTransactionType() {
		return transactionType;
	}




	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}




	public String getCurrency() {
		return currency;
	}




	public void setCurrency(String currency) {
		this.currency = currency;
	}




	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}




	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}




	@Override
	public String toString() {
		return "TransactionDto [id=" + id + ", senderAccountId=" + senderAccountId + ", receiverAccountId="
				+ receiverAccountId + ", amount=" + amount + ", transactionType=" + transactionType + ", currency="
				+ currency + ", transactionDate=" + transactionDate + "]";
	}

   
}
