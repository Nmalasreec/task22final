package com.example.demo;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.UserData;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest
{
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockmvc =MockMvcBuilders.standaloneSetup(userService).build();
	}
	
	private List<UserData> userlist = new ArrayList<>();
	
	@Test
	public void getAllUsersSuccess() throws Exception
	{
		UserData userObj = new UserData();
		
		userObj.setId(101);;
		userObj.setUsername("Noah");;
		userObj.setPassword("abcd");
		
		userlist.add(userObj);
		when(userRepo.findAll()).thenReturn(userlist);
		
		List<UserData> ulist = userService.getAllUsers();
		
		assertEquals(userlist, ulist);
		
	}
	
	
	@Test
	public void getAllUsersFailue() throws Exception
	{
		
		
		
		when(userRepo.findAll()).thenReturn(null);
		
		List<UserData> ulist = userService.getAllUsers();
		
		assertNull(ulist);
		
	}
	
	
	@Test
	public void addUserSuccess() throws Exception
	{
		UserData userObj = new UserData();
		
		userObj.setId(101);
		userObj.setUsername("Noah");
		userObj.setPassword("abcd");
		
		userlist.add(userObj);
		when(userRepo.save(any())).thenReturn(userObj);
		
		UserData u1 = userService.addUser(userObj);
		
		assertEquals(userObj, u1);
		
	}
	
	
	@Test
	public void addUserFailure() throws Exception
	{
		UserData userObj = new UserData();
		
		userObj.setId(101);
		userObj.setUsername("Noah");
		userObj.setPassword("abcd");
		
		userlist.add(userObj);
		when(userRepo.save(any())).thenReturn(null);
		
		UserData u1 = userService.addUser(userObj);
		
		assertNull( u1);
		
	}
	
	
	
	
	

}
