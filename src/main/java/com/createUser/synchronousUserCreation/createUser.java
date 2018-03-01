package com.createUser.synchronousUserCreation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.createUser.common.ApplicationConstants;
import com.createUser.domain.EmailRequest;
import com.createUser.domain.UAAEmailData;
import com.createUser.domain.UAAUserData;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;
import com.createUser.rabbitmq.RabbitMqProducer;
import com.createUser.rabbitmq.UserMailEntity;
//import com.palusers.scheduler.ScheduledTasks;
import com.createUser.services.CloudUserManageService;
import com.createUser.services.EmailCreation;

@RestController
public class createUser {
	
	private String errorinAccCreation="Error has occurred in creating user. Account might exists.";

	private static ILogger logger;	

	@Autowired
	private EmailCreation emailTemplate; 
	
	@Autowired
	private RabbitMqProducer rabbitMqProducer;
	
	@Value("${uaalogin}")
	private String uaalogin;

	@Value("${uaacreateuser}")
	private String uaacreateuser;

	
	@Value("${userpasswordlength}")
	private String userpasswordlength;

	@Value("${oauthpassword}")
	private String oauthpassword;
	
	@Value("${oauthusername}")
	private String oauthusername;

	@Value("${appmanagerurl}")
	private String appmanagerurl;
	
	@Value("${apiurl}")
	private String apiurl;
	
	@Autowired
	RestTemplate restTemplate; 
/*
	@Autowired
	private ScheduledTasks scheduledTasks; 
	*/
	@Autowired
	private CloudUserManageService cloudUsrManageService;

	@Value("${accountcreationemailsubject}")
	private String accountcreationemailsubject;	
	
	public createUser(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();
	}

	private void logError(String username, String usrMsg, String adminMsg)
	{
		logger.error(adminMsg + username);		
	}

	@CrossOrigin(origins = "${corsUrl}")
	@RequestMapping(value="/login")
	public String loginCheck()
	{
		return "Success";
	}
	
	@CrossOrigin(origins = "${corsUrl}")
	@RequestMapping(value="/creatingUser/{email}/{batch}/{expiry}/{mailOptions}", method = RequestMethod.GET)
	public String creatingUser(@PathVariable("email") String username,@PathVariable("batch") String tagName,@PathVariable("expiry") String expiryDate,@PathVariable("mailOptions") String mailOptions ) throws ParseException
	{
		int mailOption=1;
		try{
			mailOption=Integer.parseInt(mailOptions);
			if(mailOption!=1 && mailOption!=0)
				mailOption=1;
		}catch(NumberFormatException e){
			logger.error(""+e);
		}
		logger.info(" "+username+" "+tagName+" "+expiryDate+" "+mailOption);
		try
		{
			if(Character.isLetter(tagName.charAt(0)))
			{
				if(tagName.contains(" "))
					tagName=tagName.replaceAll("\\s+","");
				if(username.contains(" "))
					username=username.replaceAll("\\s+","");
				if(expiryDate.contains(" "))
					expiryDate=expiryDate.replaceAll("\\s+","");
				String orgName=null;
				String spacename="sandbox";
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    dateFormat.setLenient(false);
			    try
			    {
			    	Date expiryDate_temp = dateFormat.parse(expiryDate.trim());
			    	expiryDate=new SimpleDateFormat("ddMMyy").format(expiryDate_temp);
					logger.info("Expiry Date in required format : "+expiryDate);
					
					String token = cloudUsrManageService.getToken(uaalogin);
					logger.info("Getting Token for creating user "+ username);			
					if(token !=null)
					{
						logger.info("Token received for creating users "+ username);
						logger.info("Creating user in CF " + username);
						String password = UUID.randomUUID().toString().substring(0, Integer.parseInt(userpasswordlength));
						String newuserId = createUserInCF(token,username,password);	
						
						if(newuserId !=null)
						{
							logger.info("User created in CF " + username);					    
							logger.info("Getting Token for creating org and association "+ username);
							String oAuthtoken = cloudUsrManageService.getAuthtokenforOrgCreation(oauthusername,oauthpassword,ApplicationConstants.OAUTHGRANTTYPE);
							if(oAuthtoken !=null)
							{
								try
								{
									if(username.indexOf('.')==-1 || username.indexOf('@')==-1)
										throw new Exception("Invalid username");
									else if(username.indexOf('.') > username.indexOf('@'))
										orgName=username.substring(0,username.indexOf('@'));
									else
										orgName=username.substring(0,1)+username.substring(username.indexOf('.')+1,username.indexOf('@'));
									logger.info("Token received for creating org and association "+ username);						
									logger.info("Creating org "+ username);
									orgName=orgName+'-'+tagName+'-'+expiryDate;
									orgName=orgName.toLowerCase();
									logger.info("Org Name : " + orgName);
									String orgId = cloudUsrManageService.createOrg(oAuthtoken, orgName);
									if(orgId !=null)
									{
										logger.info("orgId : "+orgId);
										logger.info("Associating user "+username+" to org "+ orgName);
										HttpStatus resOrgtoUsr = cloudUsrManageService.associateOrgtoUser(oAuthtoken,username,orgId);
										logger.info("resOrgtoUsr - "+ resOrgtoUsr);
										if(resOrgtoUsr== HttpStatus.CREATED)
										{
											logger.info("User associated to org "+ username);
											String spaceId = cloudUsrManageService.createSpace(oAuthtoken, spacename, orgId);
											if(spaceId !=null)
											{
												logger.info("Space created "+ username);									
												logger.info("Associating user to space "+ username);
												HttpStatus resSpacetoUsr = cloudUsrManageService.associateSpacetoUser(oAuthtoken, username, spaceId);						
				
												if(resSpacetoUsr== HttpStatus.CREATED)
												{
													logger.info("User associated to space "+ username);	
													logger.info("User account created with orgs and spaces.\nUsername: " + username + " at "+ LocalDateTime.now());

													if(mailOption==1)
													{
														UserMailEntity userMailEntity = new UserMailEntity();
														
														userMailEntity.setUsername(username);
														userMailEntity.setPassword(password);
														userMailEntity.setOrgName(orgName);
														userMailEntity.setSpaceName(spacename);
														userMailEntity.setApiEndpointURL(apiurl);
														userMailEntity.setAppsManagerURL(appmanagerurl);
														
														rabbitMqProducer.sendJSONMessage(userMailEntity);
														
														//saveAccountEmail(username,accountcreationemailsubject,"User account created",password,true);
														logger.info("\nSaving Email for.." + username+"\n\n");
														return "Created CF account for "+ username;
													}
													else
														return "Created CF account for "+ username +" with password as "+ password;
													
												}
												else
												{logError(username,errorinAccCreation,"Error in associating user to space: ");}
											}
											else
											{logError(username,errorinAccCreation,"Error in creating space: ");}
										}
										else
										{logError(username,errorinAccCreation,"Error in associating user to org: ");}
									}
									else
									{logError(username,errorinAccCreation,"Error in creating org and org id is null: ");}
								}
								catch(Exception e)
								{logger.error(e.getMessage());}
							}
							else
							{logError(username,errorinAccCreation,"Oauth token is null: ");}
						}
						else
						{logError(username,errorinAccCreation,"Error in creating user in CF: ");}
					}
			    } 
			    catch (ParseException pe) 
			    {
			      logger.info("Invalid Date");
			    }
			}
			else
			{
				throw new Exception("Invalid batch tag");
			}	
		} 
		catch (Exception e)
		{
				e.printStackTrace();
		}
		
		return "There was some error while creating user.";
}
	
	private void saveAccountEmail(String username, String subject,String message,String password, boolean isSuccess)
	{
		try
		{
			EmailRequest emailData = new EmailRequest();
			emailData.Recipient = username;
			emailData.HCMRecipient = null;
			emailData.Subject = subject;
			emailData.Message = message;
			emailData.Username = username;
			emailData.Password = password;
			//emailData.AppManagerUrl = appmanagerurl;
			//emailData.ApiURL = apiurl;
			if(isSuccess)
				emailTemplate.sendAccountEmail(emailData);
			else
			{				
				emailTemplate.sendErrorMail(emailData);
				emailData.Message = message +" "+ username;
				emailTemplate.sendSupportTeamMail(emailData);
			}
			logger.info("Email Saved for.." + username);			
		}
		catch(Exception ex)
		{
			logger.error(this.getClass().getName() + ": Error in saving email");			
		}
	}
	

	private String createUserInCF(String token,String username, String password)
	{	 
		UAAUserData uaaUserData = new UAAUserData();
		uaaUserData.externalId= username;
		uaaUserData.userName= username;
		
		UAAEmailData uaaEmail = new UAAEmailData();
		uaaEmail.primary =true;
		uaaEmail.value=username;
		List<UAAEmailData> emailList= new ArrayList<UAAEmailData>();
		emailList.add(uaaEmail);
		uaaUserData.emails = emailList;
		
		uaaUserData.active = true;
		uaaUserData.verified =true;
		uaaUserData.origin="uaa";
		uaaUserData.password= password;
		List<String> lst = Arrays.asList("urn:scim:schemas:core:1.0");
		uaaUserData.schemas =lst;
		
		return cloudUsrManageService.createUser(token, uaaUserData);		
	}
}





