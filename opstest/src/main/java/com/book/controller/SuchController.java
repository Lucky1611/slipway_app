package com.book.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuchController {

	@Value("${suchname}") private String suchName;

	@RequestMapping("/hello")
	public String suchHello(){
		return "Hello " + suchName;
	}
}
