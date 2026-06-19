package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.repo.AccRepo;
import com.example.bank.repo.CustRepo;

@Service
public class CustServ {

	@Autowired
	private CustRepo cstrep;
	
	@Autowired
	private AccRepo accrep;
	
	public String add_cust(Customer c) {
		cstrep.save(c);
		return "costomer added";
	}
	public ResponseEntity<Customer> get_cust(int id){
		Optional<Customer> c = cstrep.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else return ResponseEntity.ok(c.get());
	}
	public ResponseEntity<List<Customer>> getall_cust() {
		return new ResponseEntity<>(cstrep.findAll(),HttpStatus.ACCEPTED);
		
	}
	public ResponseEntity<List<Account>> getAll_acc(int id) {
		return new ResponseEntity<>(accrep.findAllByCustomerId(id),HttpStatus.ACCEPTED);
	}
}
