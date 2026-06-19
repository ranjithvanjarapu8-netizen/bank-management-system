package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Branch_Ifsc;

@Repository
public interface BankRepo extends JpaRepository<Branch_Ifsc,String>{
	@Query("SELECT a.Ifsc FROM Branch_Ifsc AS a WHERE a.BranchName =:branchName")
	String findbybankname(@Param("branchName")String branchName);
	
	

}
