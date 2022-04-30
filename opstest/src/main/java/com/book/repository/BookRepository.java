package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.pojo.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query(value="select * from book where name like ?1%", nativeQuery=true)
	List<Book> findByName(String name);
	
	@Query(value="select * from book where author like ?1%", nativeQuery=true)
	List<Book> findByAuthorStartsWith(String author);
}
