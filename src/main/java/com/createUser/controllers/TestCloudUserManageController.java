package com.createUser.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.createUser.domain.UAAEmailData;
import com.createUser.domain.UAAUserData;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;
import com.createUser.services.CloudUserManageService;

@RestController
public class TestCloudUserManageController {

	private static ILogger logger;
	
	@Autowired
	private CloudUserManageService cloudUsrManageService;
	
	public TestCloudUserManageController(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	public ResponseEntity<String> getToken()
	{
		ResponseEntity<String> res = null;
		try
		{
			logger.info("Getting Token");
			String token = cloudUsrManageService.getToken("admin:m01JAFkBFcfUyzlmQ7NLTR3PVzGjP_Zm");
			logger.info("Token received");
			res=  new ResponseEntity<String>(token, HttpStatus.OK);			 
			return res;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/createUaaUserOrg/{token}/{orgname}", method = RequestMethod.POST)
	public ResponseEntity<String> createOrg(@PathVariable("token") String token,@PathVariable("orgname") String orgname)
	{
		//ResponseEntity<String> res = null;
		try
		{
			logger.info("Creating Org");
			String response = cloudUsrManageService.createOrg(token,orgname);
			logger.info("Org created ");
			return new ResponseEntity<String>(response, HttpStatus.OK);	
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/assoUserOrg/{token}/{orgId}/{emailId}", method = RequestMethod.PUT)
	public ResponseEntity<String> AssociateOrgToUsers(@PathVariable("token") String token,@PathVariable("orgId") String orgId,@PathVariable("emailId") String emailId)
	{
		//ResponseEntity<String> res = null;
		try
		{
			logger.info("AssociateOrgToUsers");
			HttpStatus response = cloudUsrManageService.associateOrgtoUser(token,emailId,orgId);			
			logger.info("AssociateOrgToUsers");
			return new ResponseEntity<String>("Success",response);	
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	 
	@RequestMapping(value = "/createSpace/{token}/{orgId}/{spaceName}", method = RequestMethod.POST)
	public ResponseEntity<String> CreateSpace(@PathVariable("token") String token,@PathVariable("orgId") String orgId,@PathVariable("spaceName") String spaceName)
	{		
		try
		{
			logger.info("creating space");
			String response = cloudUsrManageService.createSpace(token, spaceName, orgId);			
			logger.info("space created");
			return new ResponseEntity<String>(response, HttpStatus.OK);	
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	@RequestMapping(value = "/assoUserToSpace/{token}/{emailId}/{spaceId}", method = RequestMethod.POST)
	public ResponseEntity<String> AssocuserToSpace(@PathVariable("token") String token,@PathVariable("emailId") String emailId,@PathVariable("spaceId") String spaceId)
	{	
		try
		{
			logger.info("Associating space");
			HttpStatus response = cloudUsrManageService.associateSpacetoUser(token,"paltestuser@test.org",spaceId);			
			logger.info("Associated space");
			return new ResponseEntity<String>("Success", response);	
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	@RequestMapping(value = "/createUaaUser/{token}", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@PathVariable("token") String token)
	{
		ResponseEntity<String> res = null;
		try
		{
			UAAUserData uaaUserData = new UAAUserData();
			uaaUserData.externalId= "paltestuser";
			uaaUserData.userName= "paltestuser2@test.org";
			
			UAAEmailData uaaEmail = new UAAEmailData();
			uaaEmail.primary =true;
			uaaEmail.value="paltestuser2@test.org";
			List<UAAEmailData> emailList= new ArrayList<UAAEmailData>();
			emailList.add(uaaEmail);
			uaaUserData.emails = emailList;
			
			uaaUserData.active = true;
			uaaUserData.verified =true;
			uaaUserData.origin="uaa";
			uaaUserData.password="password123";
			List<String> lst = Arrays.asList("urn:scim:schemas:core:1.0");
			uaaUserData.schemas =lst;
			logger.info("Creating User "+ uaaUserData.userName);
			String response = cloudUsrManageService.createUser(token, uaaUserData);
			logger.info("User created "+ uaaUserData.userName);
			res=  new ResponseEntity<String>(response, HttpStatus.OK);			 
			return res;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteOperations/{orgId}/{userEmailId}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteOperations(@PathVariable("orgId") String orgId, @PathVariable("userEmailId") String userEmailId)
	{
		ResponseEntity<String> res = null;
		try
		{
			HttpStatus response = cloudUsrManageService.deleteOperations("",orgId,userEmailId);
			res=  new ResponseEntity<String>("Space Deleted",response);
			return res;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
