package com.movieflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entites.User;
import com.movieflix.repositories.UserRepo;

@Service
public class UserServiceImplements implements UserService
{
	@Autowired
	UserRepo userRepo;

	@Override
	public String createUser(User user) {
		userRepo.save(user);
		return "user created";
	}

	@Override
	public boolean userExist(String email) {
		if(userRepo.findByEmail(email)==null)
		{
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean validUser(String email, String password) {
		if(userRepo.findByEmailAndPassword(email, password)!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isAdmin(String email) {
		
		return "chetupurad20@gmail.com".equals(email);
	}

	@Override
	public List<User> viewUser() 
	{
		List<User> arr=new ArrayList<User>();
		arr=userRepo.findAll();
		return arr;
	}

	@Override
	public User profile(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateProfile(User user) 
	{
		User existingUser=userRepo.findByEmail(user.getEmail());
		existingUser.setName(user.getName());
		existingUser.setGender(user.getGender());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setPhone(user.getPhone());
		existingUser.setAddress(user.getAddress());
		
	  	userRepo.save(existingUser);
		
	}

	@Override
	public User getUser(String email) {
		
		return userRepo.findByEmail(email);
	}

	@Override
	public void updatePayment(User user) 
	{
		userRepo.save(user);
	}

	@Override
	public String deleteUser(int id) {
		userRepo.deleteById(id);
		return "User Deleted";
	}

	
	
	
	
}
