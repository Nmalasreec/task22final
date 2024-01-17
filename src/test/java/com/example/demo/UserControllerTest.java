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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.model.UserData;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest 
{
	@Mock
	private UserServiceImpl us;
	
	@InjectMocks
	private UserController uc;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(uc).build();
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
		when(us.getAllUsers()).thenReturn(userlist);
		
		List<UserData> ulist = us.getAllUsers();//redundant
		
		assertEquals(userlist, ulist);// redundant
		assertEquals(1, userlist.size());
		
	mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllUsers").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void getAllUsersFailure() throws Exception
	{
	
		userlist.clear();
		when(us.getAllUsers()).thenReturn(userlist);//pass null
		
		
		assertEquals(0, userlist.size());
		
	mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllUsers").contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
	
	@Test
	public void addUserSuccess() throws Exception
	{
		UserData userObj = new UserData();
		
		userObj.setId(101);;
		userObj.setUsername("Noah");;
		userObj.setPassword("abcd");
		
		userlist.add(userObj);
		when(us.addUser(any())).thenReturn(userObj);
		
		UserData u1 = us.addUser(userObj);
		
		
		assertEquals(1, userlist.size());
		
	mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/addUser").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(userObj)))
						.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	
	@Test
	public void addUserFailure() throws Exception
	{
		
		when(us.addUser(any())).thenReturn(null);
		
		UserData u1 = us.addUser(null);
		
		
		assertNull(u1);
		
	mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/addUser").contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(null)))
						.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}

}







