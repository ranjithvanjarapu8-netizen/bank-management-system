package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repo.AccRepo;
import com.example.bank.repo.TscRepo;

@Service
public class TrscServ {
	@Autowired
	private TscRepo tscrep;

	@Autowired
	private AccRepo accrep;
	
	public ResponseEntity<List<Transaction>> get_all_tsc(String accountnumber) {
		// TODO Auto-generated method stub
		Optional<Account> acnt = accrep.findByAccNumber(accountnumber);
		if(acnt.isEmpty()) return  ResponseEntity.notFound().build();
		
		List<Transaction> li = tscrep.findbyaccountno(accountnumber);
		return ResponseEntity.ok(li);
	}

	public ResponseEntity<List<Transaction>> get_lst_n_trsc(String accountnumber, int no) {

	    Optional<Account> acnt = accrep.findByAccNumber(accountnumber);

	    if(acnt.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    Pageable pageable = PageRequest.of(0, no);

	    List<Transaction> li =
	            tscrep.findLastNTransactions(accountnumber, pageable);

	    return ResponseEntity.ok(li);
	}
	
}
