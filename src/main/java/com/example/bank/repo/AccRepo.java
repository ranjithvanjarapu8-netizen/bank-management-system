package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Account;

@Repository
public interface AccRepo extends JpaRepository<Account,Integer> {
	@Query("SELECT MAX(a.accountNumber) FROM Account AS a")
	String findMaxAccountNumber();

}
