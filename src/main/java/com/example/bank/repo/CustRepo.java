package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Customer;

@Repository
public interface CustRepo extends JpaRepository<Customer,Integer>{

}
