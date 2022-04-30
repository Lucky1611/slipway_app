package com.book.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int user_id;
	private int book_id;
	
	
	
	public Purchase() {
		super();
	}

	public Purchase(int user_id, int book_id) {
		super();
		this.user_id = user_id;
		this.book_id = book_id;
	}
	
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", user_id=" + user_id + ", book_id=" + book_id + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
	
	
}
