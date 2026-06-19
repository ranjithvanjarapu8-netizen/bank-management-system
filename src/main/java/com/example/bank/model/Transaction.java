package com.example.bank.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String transactionType; // DEPOSIT, WITHDRAW, TRANSFER
    
    @Column(nullable = false)
    private String transactionNumber;
    
    private Double amount;

    private LocalDateTime transactionDate;

    private String description;

    private double currentBalance;
    

	@ManyToOne
    @JoinColumn(name = "account_id")
	@JsonIgnore
    private Account account;
    public Transaction() {
    	
    }public Transaction(double currentBalance) {
		super();
		this.currentBalance = currentBalance;
	}
    
	public Transaction(String transactionNumber) {
		super();
		this.transactionNumber = transactionNumber;
	}
	public Transaction(Long transactionId, String transactionType, Double amount, LocalDateTime transactionDate,
			String description, Account account) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.description = description;
		this.account = account;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionNumber=" + transactionNumber + ", amount=" + amount + ", transactionDate="
				+ transactionDate + ", description=" + description + ", currentBalance=" + currentBalance + ", account="
				+ account + "]";
	}

    // Constructors
    // Getters and Setters
}
