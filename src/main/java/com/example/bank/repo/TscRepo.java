package com.example.bank.repo;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Transaction;

@Repository
public interface TscRepo extends JpaRepository<Transaction,Long>{
	@Query("SELECT MAX(t.transactionNumber) FROM Transaction as t")
	String findMaxTransactionNumber();
	
	@Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = :accountnumber")
	List<Transaction> findbyaccountno(@Param("accountnumber") String accountnumber);
	
	@Query("""
		       SELECT t
		       FROM Transaction t
		       WHERE t.account.accountNumber = :accountNumber
		       ORDER BY t.transactionDate DESC
		       """)
		List<Transaction> findLastNTransactions(
		        @Param("accountNumber") String accountNumber,
		        org.springframework.data.domain.Pageable pageable);

}
