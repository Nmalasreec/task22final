package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserData;
import com.example.demo.service.UserServiceImpl;

@RestController
@RequestMapping("api/v1")
public class UserController 
{
	@Autowired
	private UserServiceImpl us;
	
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers()
	{
		List<UserData> userlist = us.getAllUsers();
		if(userlist.isEmpty())
		{
			return new ResponseEntity<String>("User list is empty", HttpStatus.NO_CONTENT);
		}
		else
		return new ResponseEntity<List<UserData>>(userlist, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserData user)
	{
		UserData u = us.addUser(user);
		if(u!=null)
		{
			return new ResponseEntity<String>("User is added", HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("User is not added", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	

}









