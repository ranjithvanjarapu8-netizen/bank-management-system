package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Transaction;

@Repository
public interface TscRepo extends JpaRepository<Transaction,Long>{

}
