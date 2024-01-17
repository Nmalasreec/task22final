package com.example.demo;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.model.UserData;

public class UserTest
{
	@Test
	public void test01()
	{
		UserData userObj = Mockito.mock(UserData.class); //creating mock object
		when(userObj.getUsername()).thenReturn(null);
		
		System.out.println("Mocked object result before assignment is:: "+ userObj.getUsername());
		
		UserData newObj = Mockito.mock(UserData.class);
		
		when(newObj.getUsername()).thenReturn("John");
		
	}

}
