package com.example.bank.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.repo.AccRepo;
import com.example.bank.repo.BankRepo;
import com.example.bank.repo.CustRepo;
import com.example.bank.repo.TscRepo;

@Service
public class AccServ {
	@Autowired
	private AccRepo accrep;
	@Autowired
	private BankRepo bankrep;
	
	@Autowired
	private CustRepo cusrep;
	
	@Autowired
	private TscRepo tscrep;
	
	public ResponseEntity<String> add_acc(int id, Account acc) {
		Account ac = new Account();
		Optional<Customer> customer = cusrep.findById(id);

	    if(customer.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

		ac.setAccountNumber(get_acc_no());
		ac.setAccountType(acc.getAccountType());
		ac.setBalance((double)0);
		ac.setBranchName(acc.getBranchName());
		ac.setIfscCode(bankrep.findbybankname(acc.getBranchName()));
		ac.setOpeningDate(LocalDate.now());
		ac.setCustomer(customer.get());
		ac.setStatus("ACTIVE");
		accrep.save(ac);
		return new ResponseEntity<>("ACCOUNT SUCCESSFULLY ADDED",HttpStatus.ACCEPTED);
	}
	String get_acc_no() {
		String maxAcc = accrep.findMaxAccountNumber();
		if(maxAcc==null) return "SUMINTN90001";
		long next = Long.parseLong(maxAcc.substring(7))+1;
		return "SUMINTN"+next;
		
	}
	public ResponseEntity<String> add_amnt(String accnumber,double amnt) {
		
		Optional<Account> acnt = accrep.findByAccNumber(accnumber);
		if(acnt.isEmpty()) return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
		double amount = acnt.get().getBalance()+amnt;
		acnt.get().setBalance(amount);
		return new ResponseEntity<>("Amount Deposited Successfully",HttpStatus.ACCEPTED);
	}
	
	
}
