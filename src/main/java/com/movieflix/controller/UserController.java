package com.movieflix.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.movieflix.entites.Movie;
import com.movieflix.entites.User;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("emailFromLogin")

public class UserController
{
	@Autowired
	UserService userSer;
	
	@Autowired
	 private MovieService movieService;
	
	@GetMapping
	public String home(Model model)
	{
		List<Movie> movies=movieService.viewMovie();
		model.addAttribute("movies", movies);
		return "index";
	}
	
	
	@PostMapping("createUser")
	public String createUser(@ModelAttribute User user,Model model)
	{
		if(userSer.userExist(user.getEmail())==true)
		{
			userSer.createUser(user);
			return "login";
		}
		else
		{
			model.addAttribute("message", "User already exists ");
			return "register";

		}
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute User user, Model model, HttpSession session) {
	    if (userSer.validUser(user.getEmail(), user.getPassword())) {
	        User loggedInUser = userSer.getUser(user.getEmail());
	        if (loggedInUser != null) {
	            session.setAttribute("email", loggedInUser.getEmail());
	            session.setAttribute("isPremium", loggedInUser.isPremium());
	            model.addAttribute("emailFromLogin", loggedInUser.getEmail());
	            model.addAttribute("isPremium", loggedInUser.isPremium());
	            if (userSer.isAdmin(loggedInUser.getEmail())) {
	                return "adminhome";
	            } else {
	                List<Movie> movies = movieService.viewMovie();
	                model.addAttribute("movies", movies);
	                return "welcome";
	            }
	        } else {
	            model.addAttribute("message", "User not found.");
	            return "login";
	        }
	    } else {
	        model.addAttribute("message", "Credentials not valid, try different");
	        return "login";
	    }
	}

	
	@GetMapping("viewuser")
	public String viewUser(Model model)
	{
		List<User> users=userSer.viewUser();
		model.addAttribute("users",users);
		return "viewuser";
		
	}
	
	@GetMapping("profile")
	public String profile(@ModelAttribute("emailFromLogin") String email ,Model model)
	{	
		 if (email == null) {
		        return "login"; // Redirect to login page if not authenticated
		    }

		User user=userSer.profile(email);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@GetMapping("editprofile")
	public String editProfile(@ModelAttribute("emailFromLogin") String email, Model model) {
		
	    User user = userSer.profile(email);
	    model.addAttribute("user", user);
	    return "editprofile";
	}
	

	 
	@PostMapping("updateprofile")
	public String updateprofile(@ModelAttribute User user,Model model,@ModelAttribute("emailFromLogin") String email)
	{
		if (email == null || email.isEmpty()) {
            model.addAttribute("message", "Email is required to update the profile.");
            return "login"; // Return an login page 
        }
		
        userSer.updateProfile(user);
        return "profile";
	}
	
	
	
	
	@GetMapping("logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	
	@PostMapping("deleteUser")
	public String deleteUser(@RequestParam("id") int id)
	{
		userSer.deleteUser(id);
		return "viewuser";
	}
	
	
	
}
