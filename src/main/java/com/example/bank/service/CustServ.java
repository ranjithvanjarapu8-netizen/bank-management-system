package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Customer;
import com.example.bank.repo.CustRepo;

@Service
public class CustServ {

	@Autowired
	private CustRepo cstrep;

	public String add_cust(Customer c) {
		cstrep.save(c);
		return "costomer added";
	}
}
