package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByEmailAndPassword(String email_id, String password);
	
	List<User> findUserByEmail(String email_id);
}
