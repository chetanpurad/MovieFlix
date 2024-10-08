package com.movieflix.services;

import java.util.List;

import com.movieflix.entites.User;

public interface UserService 
{
	public String createUser(User user);
	
	public boolean userExist(String email);
	
	public boolean validUser(String email, String password);
	
	public boolean isAdmin(String email);
	
	public List<User> viewUser();
	
	public User profile(String email);
	
	public void updateProfile(User user);
	
	public User getUser(String  email);
	
	public void updatePayment(User user);
	
	public String deleteUser(int id);
	
	
	

}
