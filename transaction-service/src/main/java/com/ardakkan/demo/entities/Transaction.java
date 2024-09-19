package com.ardakkan.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private String transactionType; // "TRANSFER", "WITHDRAWAL", "DEPOSIT"
    private String currency;  // TRY or USD or EUR
    private LocalDateTime transactionDate;

    
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(Long id, Long senderAccountId, Long receiverAccountId, Double amount, String transactionType,
			LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.senderAccountId = senderAccountId;
		this.receiverAccountId = receiverAccountId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderAccountId=" + senderAccountId + ", receiverAccountId="
				+ receiverAccountId + ", amount=" + amount + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + "]";
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
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

    
}
