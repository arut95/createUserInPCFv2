package com.palusers.controllers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.createUser.CloudCFApplication;
import com.createUser.controllers.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudCFApplication.class)
public class UserControllerTest {

	@Autowired
	private UserController unitController;
	
	private MockMvc mockMvc;

	@Autowired
	UserController userController;
	@Before
	public void setUp() throws Exception {
	this.mockMvc = MockMvcBuilders.standaloneSetup(unitController).build();
	MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerUserTest() throws Exception {
			MvcResult result = this.mockMvc
			.perform(post("/UserManagement/RegisterUser")
			.content("{\r\n" + 
					"	\"firstName\":\"patanjali\",\r\n" + 
					"	\"lastName\":\"patro\",\r\n" + 
					"	\"employeeId\":658631,\r\n" + 
					"	\"employeeEmailId\":\"Pushpanjali.Patro@cognizant.com\",\r\n" + 
					"	\"supervisorEmailId\":\"Sneha.Saha2@cognizant.com\",\r\n" + 
					"	\"department\":\"CTS\",\r\n" + 
					"	\"progLanguage\":\"DOTNET\",\r\n" + 
					"	\"proficiencylevel\":\"p1\",\r\n" + 
					"	\"frameworks\":\"f1\",\r\n" + 
					"	\"linuxskill\":\"l1\",\r\n" + 
					"	\"fullstackcompleted\":\"fs\",\r\n" + 
					"	\"fullstackskills\":\"fsk\",\r\n" + 
					"	\"userthoughtstraning\":\"ut\",\r\n" + 
					"	\"usercommentstraining\":\"tc\",\r\n" + 
					"	\"phoneNumber\":12353,\r\n" + 
					"	\"uniqueid\":\"734851\"\r\n" + 
					"}")
			.accept(MediaType.parseMediaType("application/json; charset=utf-8"))
			.header("Content-type", "application/json")
			.header("Authorization", "Basic cGFsdXNlcjpTaW1wbGU0dSE="))	
			.andDo(print())
			//.andExpect(status().isOk()).andExpect(content().contentType("application/json; charset=utf-8"))
			.andReturn();
			System.out.println(
			"**********************************************************************************************"
			+ "###############################################################################"
			+ result.getResponse().getContentAsString());
			assertNotNull(result.getResponse().getContentAsString());
		}
	
	@Test
	public void emailComparision() throws Exception {
			MvcResult result = this.mockMvc
			.perform(post("/UserManagement/RegisterUser")
			.content(
			"{\r\n" + 
			"	\"firstName\":\"cts\",\r\n" + 
			"	\"lastName\":\"ctslast\",\r\n" + 
			"	\"employeeId\":5456454,\r\n" + 
			"	\"employeeEmailId\":\"test.test@cognizant.com\",\r\n" + 
			"	\"supervisorEmailId\":\"test.test@cognizant.com\",\r\n" + 
			"	\"department\":\"CTS\",\r\n" + 
			"	\"progLanguage\":\"DOTNET\",\r\n" + 
			"	\"proficiencylevel\":\"p1\",\r\n" + 
			"	\"frameworks\":\"f1\",\r\n" + 
			"	\"linuxskill\":\"l1\",\r\n" + 
			"	\"fullstackcompleted\":\"fs\",\r\n" + 
			"	\"fullstackskills\":\"fsk\",\r\n" + 
			"	\"userthoughtstraning\":\"ut\",\r\n" + 
			"	\"usercommentstraining\":\"tc\",\r\n" + 
			"	\"phoneNumber\":12353,\r\n" + 
			"	\"uniqueid\":\"734851\"\r\n" + 
			"}")
			.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
			.header("Content-type", "application/json")
			.header("Authorization", "Basic cGFsdXNlcjpTaW1wbGU0dSE="))			
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
			.andReturn();
			System.out.println(
			"**********************************************************************************************"
			+ "###############################################################################"
			+ result.getResponse().getContentAsString());
			assertNotNull(result.getResponse().getContentAsString());
		}
	
	
	@Test
	public void createuserTest() throws Exception {
			MvcResult result = this.mockMvc
			.perform(post("/UserManagement/CreateUser")
			.content("{\r\n" + 
					"	\"employeeEmailId\":\"test.test@cognizant.com\",\r\n" + 
					"	\"uniqueid\":\"d36272\"\r\n" + 
					"}")
			.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
			.header("Content-type", "application/json")
			.header("Authorization", "Basic cGFsdXNlcjpTaW1wbGU0dSE="))			
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
			.andReturn();
			System.out.println(
			"**********************************************************************************************"
			+ "###############################################################################"
			+ result.getResponse().getContentAsString());
			assertNotNull(result.getResponse().getContentAsString());
		}
}