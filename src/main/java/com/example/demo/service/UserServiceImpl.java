package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserData;
import com.example.demo.repo.UserRepository;

@Service
public class UserServiceImpl 
{
	
	@Autowired
	private UserRepository userRepo;
	
	public List<UserData> getAllUsers()
	{
		
		List<UserData> userlist = (List<UserData>) userRepo.findAll();
		return userlist;
		
	}
	
	public UserData addUser(UserData user)
	{
		UserData u = userRepo.save(user);
		if(u!=null)
		{
			return user;
		}
		return null;
		
	}

	
	
	
	
	
	
}
