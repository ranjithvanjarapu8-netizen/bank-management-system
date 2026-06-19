package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Transaction;
import com.example.bank.service.TrscServ;

@RequestMapping("/Transactions")
@RestController
public class TrscCtrl {
	@Autowired
	private TrscServ tscserv;
	
	
	@GetMapping("/all/{accountnumber}")
	private ResponseEntity<List<Transaction>> get_all_tsc(@PathVariable String accountnumber){
		return tscserv.get_all_tsc(accountnumber);
	}
	
	@GetMapping("/{no}")
	private ResponseEntity<List<Transaction>> get_lst_n_trsc(@PathVariable int no,@RequestParam String accountnumber){
		return tscserv.get_lst_n_trsc(accountnumber,no);
	}
	
}
