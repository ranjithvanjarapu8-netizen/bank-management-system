package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Account;
import com.example.bank.service.AccServ;

@RequestMapping("/Account")
@RestController
public class AccCtrl {
	
	@Autowired
	public AccServ acc_serv;
	
	
	@PostMapping("/{id}")
	public ResponseEntity<String> add_acc(@PathVariable int id,@RequestBody Account acc){
		return acc_serv.add_acc(id,acc);
	}
	
}
