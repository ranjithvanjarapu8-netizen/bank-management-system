package com.example.bank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.model.Transaction;
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
		accrep.save(acnt.get());
		Transaction tsc = new Transaction();
		tsc.setAccount(acnt.get());
		tsc.setAmount(amnt);
		tsc.setTransactionDate(LocalDateTime.now());
		tsc.setTransactionType("DEPOSIT");
		tsc.setTransactionNumber(get_txn_no());
		tsc.setDescription("Amount deposited by customer in bank");
		tsc.setCurrentBalance(amount);
		tscrep.save(tsc);
		return new ResponseEntity<>("Amount Deposited Successfully",HttpStatus.OK);
	}
	public ResponseEntity<String> wd_amnt(String accnumber, double amnt) {
		Optional<Account> acnt = accrep.findByAccNumber(accnumber);
		if(acnt.isEmpty()) return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
		if(acnt.get().getBalance()<amnt) return new ResponseEntity<>("INSUFFICIENT BALANCE",HttpStatus.BAD_REQUEST);
		double amount = acnt.get().getBalance()-amnt;
		acnt.get().setBalance(amount);
		accrep.save(acnt.get());
		Transaction tsc = new Transaction();
		tsc.setAccount(acnt.get());
		tsc.setAmount(amnt);
		tsc.setTransactionNumber(get_txn_no());
		tsc.setTransactionDate(LocalDateTime.now());
		tsc.setTransactionType("WITHDRAW");
		tsc.setDescription("Amount withdraw by customer in bank");
		tsc.setCurrentBalance(amount);
		tscrep.save(tsc);
		return new ResponseEntity<>("Amount Withdraw Successfully",HttpStatus.OK);
	}
	public ResponseEntity<String> trsfr_amnt(String accnumber1, String accnumber2, double amnt) {
		if(accnumber1.equals(accnumber2)) {
		    return new ResponseEntity<>("CANNOT TRANSFER TO SAME ACCOUNT", HttpStatus.BAD_REQUEST);
		}
		Optional<Account> acnt1 = accrep.findByAccNumber(accnumber1);
		Optional<Account> acnt2 = accrep.findByAccNumber(accnumber2);
		if(acnt1.isEmpty()) return new ResponseEntity<>("SENDER ACCOUNT NOT FOUND",HttpStatus.BAD_REQUEST);
		if(acnt2.isEmpty()) return new ResponseEntity<>("RECIEVER ACCOUNT NOT FOUND",HttpStatus.BAD_REQUEST);// TODO Auto-generated method stub
		if(acnt1.get().getBalance()<amnt) return new ResponseEntity<>("INSUFFICIENT BALANCE",HttpStatus.BAD_REQUEST);
		double amount1 = acnt1.get().getBalance()-amnt;
		double amount2 = acnt2.get().getBalance()+amnt;
		acnt1.get().setBalance(amount1);
		acnt2.get().setBalance(amount2);
		accrep.save(acnt1.get());
		accrep.save(acnt2.get());
		
		String trscno = get_txn_no();
		Transaction tsc = new Transaction();
		tsc.setAccount(acnt1.get());
		tsc.setAmount(amnt);
		tsc.setTransactionNumber(trscno);
		tsc.setTransactionDate(LocalDateTime.now());
		tsc.setTransactionType("TRANSFER");
		tsc.setDescription(
			    "Transferred Rs." + amnt + " to Account " + accnumber2
			);
		tsc.setCurrentBalance(amount1);
		tscrep.save(tsc);
		
		Transaction tsc1 = new Transaction();
		tsc1.setAccount(acnt2.get());
		tsc1.setAmount(amnt);
		tsc1.setTransactionDate(LocalDateTime.now());
		tsc1.setTransactionNumber(trscno);
		tsc1.setTransactionType("TRANSFER RECEIVED");
		tsc1.setDescription(
			    "Received Rs." + amnt + " from Account " + accnumber1
			);
		tsc1.setCurrentBalance(amount2);
		tscrep.save(tsc1);
		
		return new ResponseEntity<>("Amount Tranfer Successfully",HttpStatus.OK);
		
	}
	String get_txn_no() {
	    String maxTxn = tscrep.findMaxTransactionNumber();

	    if(maxTxn == null) {
	        return "TXN10001";
	    }

	    long next = Long.parseLong(maxTxn.substring(3)) + 1;

	    return "TXN" + next;
	}
	
}
