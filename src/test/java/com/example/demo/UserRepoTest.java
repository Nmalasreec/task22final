package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.example.demo.model.UserData;
import com.example.demo.repo.UserRepository;

@DataJpaTest
@AutoConfigureMockMvc
public class UserRepoTest 
{

	@Autowired
	private UserRepository userRepo;
	
	private UserData user = new UserData();//real object
	
	@BeforeEach
	public void init()
	{
		user.setId(101);
		user.setUsername("Keith");
		user.setPassword("abcd");
	}
	
	@Test
	public void saveUserSuccess() throws Exception
	{
		UserData u1=null;
		userRepo.save(user);
		u1= userRepo.findById(user.getId()).get();
		System.out.println(u1);
		
		assertEquals(user.getUsername(), u1.getUsername());
	}
	
	@Test
	public void saveUserFailure() throws Exception
	{
		UserData u1=null;
		if(userRepo.findAll().toString().isEmpty())
		{
			userRepo.save(user);
			u1 = userRepo.findById(user.getId()).get();
		}
		
		assertNull(u1);
	}
	
}


















