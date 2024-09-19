package com.ardakkan.demo.dtos;

public class TransferRequestDto {

    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;

    // Default constructor
    public TransferRequestDto() {}

  
    public TransferRequestDto(Long senderAccountId, Long receiverAccountId, Double amount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    // Getters and setters
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
}

