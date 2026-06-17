package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Customer;
import com.example.bank.service.CustServ;

@RestController
@RequestMapping("/customer")
public class CustCtrl {

	@Autowired
	private CustServ cstsvc;
	
	@PostMapping("/new")
	private ResponseEntity<String> add_customer(@RequestBody Customer c){
		return new ResponseEntity<>(cstsvc.add_cust(c),HttpStatus.CREATED);
	}
}
