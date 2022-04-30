package com.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.pojo.Book;
import com.book.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/book/add")
	public boolean addBook(@RequestBody Book book)
	{
		try
		{
			bookRepository.save(book);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}


	@PostMapping("/book/update")
	public boolean updateBook(@RequestBody Book book)
	{
		try
		{
			bookRepository.save(book);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}

	@PostMapping("/book/delete")
	public boolean deleteBook(@RequestBody Book book)
	{
		try
		{
			bookRepository.deleteById(book.getId());
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}

	@GetMapping("/book/getAll")
	public List<Book> getAllBooks()
	{
		List<Book> books=bookRepository.findAll();
		return books;
	}

	@GetMapping("/book/getBookByName")
	public List<Book> getBooksByName(@RequestParam String name)
	{
		List<Book> books=bookRepository.findByName(name);
		return books;
	}

	@GetMapping("/book/getBookByAuthor")
	public List<Book> getBooksByAuthor(@RequestParam String author)
	{
		List<Book> books=bookRepository.findByAuthorStartsWith(author);
		return books;
	}




}
