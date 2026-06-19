package com.example.bank.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Account;

@Repository
public interface AccRepo extends JpaRepository<Account,Integer> {
	@Query("SELECT MAX(a.accountNumber) FROM Account AS a")
	String findMaxAccountNumber();
	@Query("SELECT a FROM Account AS a WHERE a.accountNumber =:accnumber")
	Optional<Account> findByAccNumber(@Param("accnumber") String accnumber);
	
	@Query("SELECT a FROM Account AS a WHERE a.customer.customer_id = :id")
	List<Account> findAllByCustomerId(@Param("id") int id);

}
