package com.book.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.pojo.User;
import com.book.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user/register")
	public String registerUser(@RequestBody User user){
		if(userRepository.findUserByEmail(user.getEmail_id()).size()>0)
		{
			return "Email id exists, please enter new email Id!";
		}
		userRepository.save(user);
		return "User Added";
	}

	@PostMapping("/user/login")
	public boolean loginUser(@RequestBody User user, HttpSession session)
	{
		List<User> userList=userRepository.findByEmailAndPassword(user.getEmail_id(), user.getPassword());
		if(userList.size()>=1) {
			session.setAttribute("LOGIN_ID", userList.get(0).getId());
			return true;
		}
		else return false;
	}

	@RequestMapping("/user/logout")
	public ModelAndView logoutUser(HttpSession session)
	{
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;	  
	}

	@RequestMapping("/user/pages/redirectPage")
	public ModelAndView getRedirectPage()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect");
		return modelAndView;	  
	}

	@RequestMapping("/user/pages/registerPage")
	public ModelAndView getRegisterPage()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/register");
		return modelAndView;	  
	}

	@RequestMapping("/user/pages/loginPage")
	public ModelAndView getLoginPages()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;	  
	}

	@RequestMapping("/user/pages/homePage")
	public ModelAndView getHomePages(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("LOGIN_ID")==null)
		{
			modelAndView.setViewName("user/login");
			return modelAndView;
		}
		modelAndView.setViewName("user/home");
		return modelAndView;	  
	}

	@RequestMapping("/user/hello")
	public String HelloUser(){
		return "hello from user class";
	}

}
