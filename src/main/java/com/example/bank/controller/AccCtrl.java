package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
	
	@PutMapping("/deposit")
	public ResponseEntity<String> add_amnt(@RequestParam String Accnumber,@RequestParam double amnt){
		return acc_serv.add_amnt(Accnumber,amnt);
	}
	@PutMapping("/credit")
	public ResponseEntity<String> wd_amnt(@RequestParam String Accnumber,@RequestParam double amnt){
		return acc_serv.wd_amnt(Accnumber,amnt);
	}
	@PutMapping("/transfer")
	public ResponseEntity<String> Trsfr_amnt(@RequestParam String Accnumber1,
												@RequestParam String Accnumber2,@RequestParam double amnt){
		return acc_serv.trsfr_amnt(Accnumber1,Accnumber2,amnt);
	}
	
}
