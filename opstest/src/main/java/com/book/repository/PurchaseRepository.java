package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.pojo.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>  {
	
	
}
