package com.createUser.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.createUser.common.ApplicationConstants;
import com.createUser.domain.EmailRequest;
import com.createUser.domain.UserCreateRequest;
import com.createUser.domain.UserEntity;
import com.createUser.domain.UserRequest;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RefreshScope
@RestController
@RequestMapping("/UserManagement")
@CrossOrigin
@Api(value = "Cloud foundry User Management")
public class UserController {
	private static ILogger logger;
	private UserEntity fUserEntity;
	
	
	@Value("${welcomepalemailsubject}")
	private String welcomepalemailsubject;
	
	@Value("${invalidemailsubject}")
	private String invalidemailsubject;
	
	@Value("${invalidemailmsg}")
	private String invalidemailmsg;
	
	@Value("${invalidaccountsubject}")
	private String invalidaccountsubject;
	
	@Value("${invalidaccountmsg}")
	private String invalidaccountmsg;
	
	@Value("${maliciousemailsubject}")
	private String maliciousemailsubject;
	
	@Value("${invaliduniqueidsubject}")
	private String invaliduniqueidsubject;
	
	@Value("${invaliduniqueidmsg}")
	private String invaliduniqueidmsg;

	@Value("${maliciousemailmsg}")
	private String maliciousemailmsg;
		
	//@Value("${limitmailsubject}")
	private String limitmailsubject = "User Limit Exceeded";
	
	//@Value("${userlimit}")
	private int userlimit = 5;
		
	public UserController(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();
	}
/*
	@ApiOperation(value ="Delete a particular user")
	@RequestMapping(value = "/DeleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)
	{		
		try
		{	
			userservice.delete(id);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}
		catch(Exception ex)
		{			
			logger.error(ex.getMessage());
			return new ResponseEntity<String>("Error in Deleting", HttpStatus.OK);
		}
	}
	
	@ApiOperation(value ="Get all users")
	@RequestMapping(value = "/GetAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getAllUsers()
	{
		ResponseEntity<List<UserEntity>> reponse = null;
		try
		{	
			List<UserEntity> usrList = userservice.listAll();
			if(usrList !=null && !usrList.isEmpty())
			{
				reponse = new ResponseEntity<List<UserEntity>>(usrList, HttpStatus.OK);
			}
			else
			{
				reponse = new ResponseEntity<List<UserEntity>>(usrList, HttpStatus.OK);
			}
			return reponse;
		}
		catch(Exception ex)
		{			
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@ApiOperation(value ="Reguster a user")
	@RequestMapping(value = "/RegisterUser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest)
	{		
		try
		{
			setUserDetails(userRequest);
			UserEntity userEntity= registerinDB();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=utf-8");
			return new ResponseEntity<UserEntity>(userEntity,headers, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@ApiOperation(value ="Create a user in cloud foundry")
	@RequestMapping(value = "/CreateUser", method = RequestMethod.POST)
	public ResponseEntity<String> createuser(@RequestBody UserCreateRequest usercreateRequest)
	{	
		try
		{
			HttpHeaders headers = new HttpHeaders();
			//headers.add("Content-Type", "application/json; charset=utf-8");
			
			UserEntity usrObj = userservice.findbyEmailId(usercreateRequest.employeeEmailId.trim().toLowerCase());
			if(usrObj !=null)
			{
				if(usrObj.getUniqueid() !=null && !usrObj.getUniqueid().trim().equalsIgnoreCase(usercreateRequest.uniqueid))
				{
					sendEmail(invaliduniqueidsubject,invaliduniqueidsubject,false,false);					
					return new ResponseEntity<String>(ApplicationConstants.FAILURE,headers, HttpStatus.OK);
				}
				if(usrObj.getUniqueid() !=null && usrObj.getUniqueid().trim().equalsIgnoreCase(usercreateRequest.uniqueid) && !usrObj.getActivationstatus().trim().equalsIgnoreCase(ApplicationConstants.ActivationStatus.NOTACTIVATED.name()))
				{
					sendEmail(invalidaccountsubject,invalidaccountmsg,false,false);					
					return new ResponseEntity<String>(ApplicationConstants.FAILURE, headers,HttpStatus.OK);
				}
			}
			else
			{
				sendEmail(maliciousemailsubject,maliciousemailmsg +usercreateRequest.employeeEmailId,false,true);
			}
			userservice.updateActStatus(usercreateRequest.employeeEmailId, ApplicationConstants.ActivationStatus.TOBEACTIVATED.name(),Timestamp.valueOf(LocalDateTime.now()));
			return new ResponseEntity<String>(ApplicationConstants.SUCCESS,headers, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	private void setUserDetails(UserRequest userRequest)
	{
		this.fUserEntity = new UserEntity();
		this.fUserEntity.setFirstname(userRequest.firstName);
		this.fUserEntity.setLastname(userRequest.lastName);
		this.fUserEntity.setEmployeeid(userRequest.employeeId);
		this.fUserEntity.setEmployeeemailid(userRequest.employeeEmailId.toLowerCase());
		this.fUserEntity.setSupervisoremailid(userRequest.supervisorEmailId.toLowerCase());
		this.fUserEntity.setDepartment(userRequest.department);
		this.fUserEntity.setProglanguage(userRequest.progLanguage);
		this.fUserEntity.setProficiencylevel(userRequest.proficiencylevel);
		this.fUserEntity.setFrameworks(userRequest.frameworks);
		this.fUserEntity.setLinuxskill(userRequest.linuxskill);
		this.fUserEntity.setFullstackcompleted(userRequest.fullstackcompleted);
		this.fUserEntity.setFullstackskills(userRequest.fullstackskills);
		this.fUserEntity.setUserthoughtstraning(userRequest.userthoughtstraning);
		this.fUserEntity.setUsercommentstraining(userRequest.usercommentstraining);
		this.fUserEntity.setPhonenumber(userRequest.phoneNumber);
		this.fUserEntity.setTimezone(getTimeZone());
		this.fUserEntity.setUniqueid("a" + UUID.randomUUID().toString().substring(0,6));
		this.fUserEntity.setActivationstatus(ApplicationConstants.ActivationStatus.NOTACTIVATED.name());
		this.fUserEntity.setNumberofattempts(0);
		this.fUserEntity.setCreationdate(Timestamp.valueOf(LocalDateTime.now()));
	}
	
	private String getTimeZone()
	{
		Calendar now = Calendar.getInstance();
		TimeZone timeZone = now.getTimeZone();
		return timeZone.getDisplayName();
	}
	
	private UserEntity registerinDB()
	{
		UserEntity usrObj = null;
		if(fUserEntity.getEmployeeemailid().trim().equalsIgnoreCase(fUserEntity.getSupervisoremailid().trim()))
		{
			logger.info("User's email and Supervisor email cannot be same: " + fUserEntity.getEmployeeemailid());
			logger.info("Svaving Email for.." + fUserEntity.getEmployeeemailid());
			sendEmail(invalidemailsubject,invalidemailmsg,false,false);
			logger.info("Email Saved for.." + fUserEntity.getEmployeeemailid());
			usrObj =null;
		}
		else
		{
			UserEntity usrEntityResult = userservice.findbyEmailId(fUserEntity.getEmployeeemailid().trim().toLowerCase());
			if(usrEntityResult !=null)
			{
				if(usrEntityResult.getEmployeeemailid() !=null && usrEntityResult.getEmployeeemailid().trim().equalsIgnoreCase(this.fUserEntity.getEmployeeemailid().trim()))
				{
					logger.info("User's email already registered in DB " + fUserEntity.getEmployeeemailid());
					logger.info("Saving Email for.." + fUserEntity.getEmployeeemailid());
					sendEmail(invalidaccountsubject,invalidaccountmsg,false,false);
					logger.info("Email Saved for.." + fUserEntity.getEmployeeemailid());
					usrObj =null;											
				}
			}
			else
			{
				    Date currentDate = new Date();
				    int limit = userlimit;		    
				    String start = "00:00:00";
                    SimpleDateFormat sdf = new SimpleDateFormat("MM"); 
					SimpleDateFormat sdfyear = new SimpleDateFormat("YYYY");
					String month = sdf.format(currentDate);
					String year = sdfyear.format(currentDate);
       		        String getFrom = year+"-"+month+"-"+"01 "+start;
				    Long c = userservice.getLongCount(Timestamp.valueOf(getFrom),Timestamp.valueOf(LocalDateTime.now()));
				    int count = c.intValue();
				   
					logger.info("***Current Date**"+currentDate);
				    logger.info("***User Entity Creation Date**"+fUserEntity.getCreationdate());
					logger.info("****Month***"+sdf.format(currentDate));
					logger.info("****Year***"+sdfyear.format(currentDate));					
			        logger.info("****Timestamp***"+Timestamp.valueOf(getFrom));
			        logger.info("****Timestamp***"+Timestamp.valueOf(LocalDateTime.now()));	
				    logger.info("*************************User Count in DB..******************" + count);
				  				    
					if(count<=limit)
					{
					usrObj = userservice.saveOrUpdate(fUserEntity);
					sendEmail(welcomepalemailsubject,"",true,false);
					}	
				
					else
					{
					sendEmail(limitmailsubject,"",true,true);
					}
			}
		}
		return usrObj;
	}
	
	
	private boolean sendEmail(String subject,String message, boolean isSuccess, boolean Istoadmin)
	{
		try
		{		
			EmailRequest emailData = new EmailRequest();
			emailData.Recipient = fUserEntity.getEmployeeemailid();
			emailData.HCMRecipient = fUserEntity.getSupervisoremailid();			
			emailData.Subject = subject;
			emailData.Message = message;	
			emailData.Track = fUserEntity.getProglanguage();
			//TODO NEED TO REMOVE BELOW CODE ONCE TESTED
			//if(fUserEntity.getProglanguage().equals(".NET"))// ApplicationConstants.ProgLanguage.DOTNET.name()))
			//{
				//emailData.LearningUrl = dotneturl;
			//}
			//else if(fUserEntity.getProglanguage().equals(ApplicationConstants.ProgLanguage.JAVA.name()))
			//{
				//emailData.LearningUrl = javaurl;
			//}
			emailData.UniqueId = fUserEntity.getUniqueid();
			//emailData.AccountCreationUrl = accountcreationurl;
			if(isSuccess)
			{
							
				if(Istoadmin)
				{
					emailservice.sendLimitEmail(emailData);
				}
				else
					emailservice.sendRegistrationEmail(emailData);
		   // emailservice.sendSupportTeamMail(emailData);
			}
			else
			{
				if(Istoadmin)
					emailservice.sendSupportTeamMail(emailData);
				else
					emailservice.sendErrorMail(emailData);
			}	
			logger.info("Email Saved for.." + fUserEntity.getEmployeeemailid());
			return true;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return false;
		}
	}
	*/
}
