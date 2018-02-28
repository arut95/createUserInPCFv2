package com.createUser.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.createUser.domain.TestUserRequest;
import com.createUser.domain.UserEntity;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;
import com.createUser.services.UserCreationService;

@RestController
@RequestMapping("/Users")
public class TestUserController {
	
	private static ILogger logger;	
	@Autowired
	private UserCreationService userservice;
	
	
	public TestUserController(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();		
	}
	
	@RequestMapping(value = "/RegisterUser", method = RequestMethod.POST)
	public String registerUser(@RequestBody TestUserRequest userRequest)
	{		
		try
		{
			UserEntity fUserEntity = new UserEntity();
			fUserEntity.setFirstname(userRequest.firstname);
			fUserEntity.setLastname(userRequest.lastname);
			fUserEntity.setEmployeeid(userRequest.employeeid);
			fUserEntity.setEmployeeemailid(userRequest.employeeemailid);
			fUserEntity.setSupervisoremailid(userRequest.supervisoremailid);
			fUserEntity.setDepartment(userRequest.department);
			fUserEntity.setProglanguage(userRequest.proglanguage);
			fUserEntity.setPhonenumber(userRequest.phonenumber);
			fUserEntity.setTimezone(userRequest.timezone);
			fUserEntity.setUniqueid(userRequest.uniqueid);
			fUserEntity.setActivationstatus(userRequest.activationstatus);
			if(userRequest.activationdate !=null)
			{
				Timestamp timestamp = Timestamp.valueOf(userRequest.activationdate);
				//Date date = new Date(timestamp.getTime());
				fUserEntity.setActivationdate(timestamp);
			}
			if(userRequest.creationdate != null)
			{
				Timestamp timestamp = Timestamp.valueOf(userRequest.creationdate);
				//Date date = new Date(timestamp.getTime());
				fUserEntity.setCreationdate(timestamp);
			}
			if(userRequest.lastmodifieddate != null)
			{
				Timestamp timestamp = Timestamp.valueOf(userRequest.lastmodifieddate);
				//Date date = new Date(timestamp.getTime());
				fUserEntity.setLastmodifieddate(timestamp);
			}			 
			fUserEntity.setNumberofattempts(userRequest.numberofattempts);
			fUserEntity.setOrgid(userRequest.orgid);
			fUserEntity.setProficiencylevel(userRequest.proficiencylevel);
			fUserEntity.setFrameworks(userRequest.frameworks);
			fUserEntity.setLinuxskill(userRequest.linuxskill);
			fUserEntity.setFullstackcompleted(userRequest.fullstackcompleted);
			fUserEntity.setFullstackskills(userRequest.fullstackskills);
			fUserEntity.setUserthoughtstraning(userRequest.userthoughtstraning);
			fUserEntity.setUsercommentstraining(userRequest.usercommentstraining);			
			System.out.println(fUserEntity.getFirstname());
			userservice.saveOrUpdate(fUserEntity);
			return "OK";
			//UserEntity userEntity= userservice.saveOrUpdate(fUserEntity);
			//HttpHeaders headers = new HttpHeaders();
			//headers.add("Content-Type", "application/json; charset=utf-8");
			//return new ResponseEntity<UserEntity>(userEntity,headers, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			//return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			return "Error";
		}		
	}
}
