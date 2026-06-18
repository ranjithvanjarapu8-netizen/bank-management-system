package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Account;
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
	@GetMapping("/{id}")
	private ResponseEntity<Customer> get_cus(@PathVariable int id){
		return cstsvc.get_cust(id);
	}
	@GetMapping("/all")
	private ResponseEntity<List<Customer>> getall_cust(){
		return cstsvc.getall_cust();
	}
	@GetMapping("/Account/{id}")
	private ResponseEntity<List<Account>> getAll_acc(@PathVariable int id){
		return cstsvc.getAll_acc(id);
	}
}
