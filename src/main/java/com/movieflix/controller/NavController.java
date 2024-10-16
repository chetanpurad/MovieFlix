package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.movieflix.entites.Movie;
import com.movieflix.entites.User;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController 
{
	@Autowired
	MovieService movieService;
	
	@GetMapping("register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("login")
	public String login()
	{
		return "login";
	}
	

	@GetMapping("adminhome")
	public String adminhome()
	{
		return "adminhome";
	}
	
	@GetMapping("map-addmovie")
	public String addmovie()
	{
		return "addmovie";
	}
	@Autowired
	UserService service;
	
	@GetMapping("welcome")
	public String userHome(Model model,HttpSession session)
	{	 
		 String email = (String) session.getAttribute("email");
	        if (email != null) {
	            User user = service.getUser(email);
	            boolean isPremium = user.isPremium();
	            List<Movie> movies = movieService.viewMovie();
	            model.addAttribute("movies", movies);
	            model.addAttribute("isPremium", isPremium);
	            return "welcome"; // or "welcome" based on your view name
	        }
	        return "login"; // Redirect to login if the session is not valid
	    }
	
	
	@GetMapping("payment")
	public String payment()
	{
		return "payment";
	}
	
	@GetMapping("forgotpassword")
	public String forgotPassword()
	{
		return "forgotpassword";
	}
	
}
