package com.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.pojo.Purchase;
import com.book.repository.PurchaseRepository;


@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseRepository purchaseRepo;
	
	@GetMapping("/purchase/book")
	public boolean purchaseBook(@RequestParam int bookId, HttpSession session)
	{
		try {
			int userId=(int)session.getAttribute("LOGIN_ID");
			System.out.println(userId+ " " + bookId );
			
			Purchase purchase=new Purchase(userId, bookId);
			purchaseRepo.save(purchase);
			return true;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	@GetMapping("/purchase/history")
	public List<Purchase> getAllPurchase()
	{
		return purchaseRepo.findAll();
	}
	
}
