package com.example.bank.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Account_id;
	
	@Column(unique = true, nullable = false)
	private String accountNumber;

    private String accountType; // SAVINGS, CURRENT

    private Double balance;

    private LocalDate openingDate;
    private String branchName;
    private String ifscCode;
    private String status; // ACTIVE, CLOSED, BLOCKED
    
    @OneToMany(mappedBy = "account",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    public Account() {
    }
	public Account(List<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}

	public Account(String branchName, String ifscCode) {
		super();
		this.branchName = branchName;
		this.ifscCode = ifscCode;
	}

	public Account(Integer account_id, String accountNumber, String accountType, Double balance, LocalDate openingDate,
			String status, Customer customer) {
		super();
		Account_id = account_id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.openingDate = openingDate;
		this.status = status;
		this.customer = customer;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Integer getAccount_id() {
		return Account_id;
	}

	public void setAccount_id(Integer account_id) {
		Account_id = account_id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Override
	public String toString() {
		return "Account [Account_id=" + Account_id + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", balance=" + balance + ", openingDate=" + openingDate + ", branchName=" + branchName + ", ifscCode="
				+ ifscCode + ", status=" + status + ", transactions=" + transactions + ", customer=" + customer + "]";
	}
    
}
