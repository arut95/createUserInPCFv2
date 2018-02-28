package com.createUser.cloudAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.createUser.domain.UAAUserData;
import com.createUser.logger.ILogger;
import com.createUser.logger.LoggerFactory;
import com.createUser.utils.SSLCertificateValidation;

@RefreshScope
@Component
public class ManageUsers {

	private static ILogger logger;
	
	@Autowired
	RestTemplate restTemplate; 
	
	@Value("${uaaloginurl}")
	private String uaaloginurl;
	
	@Value("${uaacreateuser}")
	private String uaacreateuser;
	
	@Value("${uaacreateorg}")
	private String uaacreateorg;
	
	@Value("${uaacreatespace}")
	private String uaacreatespace;
	
	@Value("${uaadeleteapps}")
	private String uaadeleteapps;

	@Value("${uaadeletespaceroute}")
	private String uaadeletespaceroute;
		
	@Value("${uaadeleteservice}")
	private String uaadeleteservice;
	
	@Value("${uaadeleteusers}")
	private String uaadeleteusers;
	
	@Value("${oauthToken}")
	private String oauthToken;
	
	
	public ManageUsers(LoggerFactory loggerFactory)
	{
		logger = loggerFactory.getLoggerInstance();
	}
	public String getToken(String credentials) {
		String token =null;
		try
		{	
			String plainCreds = credentials;//propertyFileReader.getMessage(ApplicationConstants.UAALOGINCREND);
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
	
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "Basic " + base64Creds);
			
			HttpEntity<String> request = new HttpEntity<String>(headers);
			SSLCertificateValidation.disable();
			
			ResponseEntity<String> response = restTemplate.exchange(uaaloginurl, HttpMethod.POST, request, String.class);
			logger.info("GetToken "+ response.getStatusCode());
			if(response.getStatusCode() == HttpStatus.OK)
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(response.getBody());
				JSONObject resultObj = (JSONObject) obj;
				token = (String) resultObj.get(com.createUser.common.ApplicationConstants.ACCESS_TOKEN);
			}
			return token;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return token;
		}
	}

	public String createUser(String token, UAAUserData uaaUserData) {
		String newuserId =null;
		try
		{	
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "Bearer " + token);
			HttpEntity<?> request = new HttpEntity<Object>(uaaUserData,headers);
			SSLCertificateValidation.disable();		
			ResponseEntity<String> response = restTemplate.exchange(uaacreateuser, HttpMethod.POST, request,String.class);			
			logger.info("CreateUser "+ response.getStatusCode());
			if(response.getStatusCode() == HttpStatus.CREATED)
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(response.getBody());
				JSONObject resultObj = (JSONObject) obj;
				newuserId = (String) resultObj.get("id");
			}
			return newuserId;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return newuserId;
		}
	}
	
	public String deleteUsersAndOrgs(String token){
		try
		{	
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "bearer "+token);
			HttpEntity<?> request = new HttpEntity<Object>(headers);
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(uaacreateorg+"?order-by=name&order-direction=asc&page=1", HttpMethod.GET, request, String.class);

			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response.getBody());
			int noOfPages=Integer.parseInt(""+json.get("total_pages"));
			json = (JSONObject) parser.parse(response.getBody());
			int noOfResults=Integer.parseInt(""+json.get("total_results"));
			logger.info("No. of pages : "+noOfPages);
			logger.info("Total no. of Orgs : "+noOfResults);
			
			String deletedList="";
			for(int page=1;page<=noOfPages;page++)
			{
				ResponseEntity<String> response1;
				if(page==1)
					response1 = response;
				else
					response1 = restTemplate.exchange(uaacreateorg+"?order-by=name&order-direction=asc&page="+page, HttpMethod.GET, request, String.class);
					
				for(Object res : parseResponse(response1)) 
				{
					String orgName = (String) ((JSONObject)((JSONObject)res).get("entity")).get("name");
					String orgGuid = (String) ((JSONObject)((JSONObject)res).get("metadata")).get("guid");
					
					if(orgName.matches(".*-\\d{6}"))
					{
						logger.info("Found an org which matches *-111111 format - "+orgName);
						int len=orgName.length();
						Date expirydate = new SimpleDateFormat("ddMMyy").parse(orgName.substring(len-6, len)); 
						String expiryDate = orgName.substring(len-6, len); //Trimming only the Expiry date 
						SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
					    dateFormat.setLenient(false);
					    try 
					    {
					    	expirydate = dateFormat.parse(expiryDate.trim()); //Parsing string to date format
					    	Date currDate = new Date(); //Getting current date
					        long diffBetweenDates = currDate.getTime() - expirydate.getTime();
					        if(diffBetweenDates >= 0)
					        {	
								logger.info("An org is found to have crossed the expiry date - "+orgName);
								ResponseEntity<String> response2 = restTemplate.exchange(uaacreateorg+"/"+orgGuid+"/users", HttpMethod.GET, request, String.class);
								String userId=null,userName=null;
								for(Object res1 : parseResponse(response2)) 
								{
									userId = (String) ((JSONObject)((JSONObject)res1).get("metadata")).get("guid");
									userName = (String) ((JSONObject)((JSONObject)res1).get("entity")).get("username");
									logger.info("Finding the user for org - "+orgName);
									logger.info("Username of the org is - "+userName);
									if(!(userName.isEmpty() || userName.equals("admin") || userName.equals("pcfadmin")))
									{
										logger.info("Deleting org - "+orgName);
										logger.info("Org Id : "+orgGuid);
										logger.info("Deleting user - "+userName);
										logger.info("User Id : "+userId);
										deleteOperations(token,orgGuid,userName);
										logger.info("\nDeleted :\nOrg Name : "+orgName+"\nUsername: "+userName+"\nOrg guid : "+orgGuid+"\nOrg user : "+userId+"\n");
										deletedList+="\nDeleted :\nOrg Name : "+orgName+"\nUsername: "+userName+"\nOrg guid : "+orgGuid+"\nOrg user : "+userId+"\n";
									}
								}
					        }	
					    } 
					    catch (ParseException pe) 
					    {
					    	logger.error("Date is of a wrong format or the date is a wrong value for the org "+orgName);
					    }
					}
				}
			}
			return deletedList;
		}
		catch(Exception ex){
			logger.error(ex.getMessage());}
		return null;
	}
	
	public String createOrg(String token, String orgName) {
		String orgid=null;
		try
		{	
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "bearer "+token);
			HttpEntity<?> request = new HttpEntity<Object>("{\"name\": \""+orgName+ "\"}",headers);
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(uaacreateorg, HttpMethod.POST, request, String.class);
			logger.info("CreateOrg "+ response.getStatusCode());
			if(response.getStatusCode() == HttpStatus.CREATED)
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(response.getBody());
				JSONObject resultObj = (JSONObject) obj;	        
				orgid = (String) ((JSONObject)resultObj.get("metadata")).get("guid");				
			}
			return orgid;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return orgid;
		}
	}
	
	public HttpStatus deleteOrg(String token, String orgId) {		
		try
		{	
			logger.info("deleting orgid: "+orgId);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "bearer "+token);
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			HttpEntity<String> request = new HttpEntity<String>(headers);
			SSLCertificateValidation.disable();
			logger.info("deleting orgid url: "+uaacreateorg+"/"+orgId);
			ResponseEntity<String> response = restTemplate.exchange(uaacreateorg+"/"+orgId+"?recursive=true&async=true", HttpMethod.DELETE, request, String.class);
			logger.info("deleted orgid: "+orgId);
			return response.getStatusCode();		
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public HttpStatus associateOrgtoUser(String token,String emailId,String orgId) {
		try
		{	
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "bearer "+token);			
			HttpEntity<?> request = new HttpEntity<Object>("{\"username\": \""+emailId+ "\"}",headers);		
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(uaacreateorg+"/"+orgId+"/users", HttpMethod.PUT, request, String.class);
			return response.getStatusCode();
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public String createSpace(String token, String spaceName,String orgId) {
		String spaceId =null;
		try
		{			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "bearer "+token);			
			HttpEntity<?> request = new HttpEntity<Object>("{\"name\": \""+spaceName+"\",\"organization_guid\": \""+orgId+"\"}",headers);
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(uaacreatespace, HttpMethod.POST, request, String.class);
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse(response.getBody());
	        JSONObject resultObj = (JSONObject) obj;	        
	        spaceId = (String) ((JSONObject)resultObj.get("metadata")).get("guid");
			return spaceId;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return spaceId;
		}
	}
	
	public HttpStatus associateSpacetoUser(String token,String emailId,String spaceId) {
		try
		{
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("Authorization", "bearer "+token);			
			HttpEntity<?> request = new HttpEntity<Object>("{\"username\": \""+emailId+ "\"}",headers);
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(uaacreatespace+"/"+spaceId+"/developers", HttpMethod.PUT, request, String.class);
			
			return response.getStatusCode();
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public String getAuthToken(String username,String password,String grant_type) {
		String accessToken =null;
		try
		{	
			HttpHeaders headers = new HttpHeaders();			
			headers.add("Authorization", "Basic Y2Y6");
			HttpEntity<String> request = new HttpEntity<String>(headers);
			SSLCertificateValidation.disable();
			ResponseEntity<String> response = restTemplate.exchange(oauthToken+"?username="+username+"&password="+password+"&grant_type="+grant_type, HttpMethod.GET, request, String.class);
			if(response.getStatusCode() == HttpStatus.OK)
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(response.getBody());
				JSONObject resultObj = (JSONObject) obj;
				accessToken = (String) resultObj.get("access_token");
				return accessToken;
			}
			return accessToken;
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return accessToken;
		}
	}

	public HttpStatus deleteOperations(String token, String orgGuiID, String userEmailId) {

		try
		{

			logger.info("token --> "+token);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "bearer "+token);
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			HttpEntity<String> request = new HttpEntity<String>(headers);
			SSLCertificateValidation.disable();

			ResponseEntity<String> response = restTemplate.exchange(uaacreateorg+"/"+orgGuiID+"/spaces", HttpMethod.GET, request, String.class);

			for(Object spaces : parseResponse(response))
			{
				String spaceId = (String) ((JSONObject)((JSONObject)spaces).get("metadata")).get("guid");
				
				ResponseEntity<String> response1 = restTemplate.exchange(uaacreatespace+"/"+spaceId+"/apps", HttpMethod.GET, request, String.class);

				for(Object apps : parseResponse(response1)) {
					String appid = (String) ((JSONObject)((JSONObject)apps).get("metadata")).get("guid");
					
					ResponseEntity<String> responsesb = restTemplate.exchange(uaadeleteapps+"/"+appid+"/service_bindings", HttpMethod.GET, request, String.class);
					for(Object services : parseResponse(responsesb)) {
						String serviceid = (String) ((JSONObject)((JSONObject)services).get("metadata")).get("guid");
						logger.info("unbinding serviceid: "+serviceid);
						ResponseEntity<String> responseub = restTemplate.exchange(uaadeleteapps+"/"+appid+"/service_bindings/"+serviceid, HttpMethod.DELETE, request, String.class);
						logger.info("unbinded serviceid: "+serviceid);
					}
					
					logger.info("deleting appid: "+appid);
					logger.info("deleting appid url: "+uaadeleteapps + "/" + appid);
					ResponseEntity<String> response2 = restTemplate.exchange(uaadeleteapps+"/"+appid, HttpMethod.DELETE, request, String.class);
					logger.info("deleted appid: "+appid);
				}

				ResponseEntity<String> response3 = restTemplate.exchange(uaacreatespace+"/"+spaceId+"/service_instances", HttpMethod.GET, request, String.class);

				for(Object services : parseResponse(response3)) {
					String serviceId = (String) ((JSONObject)((JSONObject)services).get("metadata")).get("guid");
					logger.info("deleting serviceid: "+serviceId);
					logger.info("deleting serviceId url: "+uaadeleteservice + "/" + serviceId);
					ResponseEntity<String> response4 = restTemplate.exchange(uaadeleteservice + "/" + serviceId, HttpMethod.DELETE, request, String.class);
					logger.info("deleted serviceid: "+serviceId);
				}
				
				ResponseEntity<String> getspaceroutes = restTemplate.exchange(uaacreatespace+"/"+spaceId+"/routes", HttpMethod.GET, request, String.class);

				for(Object routespace : parseResponse(getspaceroutes)) {
					String routeid = (String) ((JSONObject)((JSONObject)routespace).get("metadata")).get("guid");
					logger.info("deleting routeid: "+routeid);
					logger.info("deleting routeid url: "+uaadeletespaceroute + "/" + routeid);
					ResponseEntity<String> response4 = restTemplate.exchange(uaadeletespaceroute + "/" + routeid+"?recursive=true", HttpMethod.DELETE, request, String.class);
					logger.info("deleted routeid: "+routeid);
				}

				deleteSpace(token,spaceId);
			}

			ResponseEntity<String> userResponse = restTemplate.exchange(uaacreateorg+"/"+orgGuiID+"/users", HttpMethod.GET, request, String.class);

			logger.info("userresp --"+userResponse);

			for(Object users : parseResponse(userResponse)) {

				String emailId = (String) ((JSONObject) ((JSONObject) users).get("entity")).get("username");

				logger.info("emailId-->" + emailId);

				if (userEmailId.equalsIgnoreCase(emailId)) {
					logger.info("deleting userid: "+emailId);
					String userId = (String) ((JSONObject) ((JSONObject) users).get("metadata")).get("guid");
					logger.info("deleting userid url: "+uaadeleteusers + "/" + userId);
					ResponseEntity<String> response1 = restTemplate.exchange(uaadeleteusers + "/" + userId, HttpMethod.DELETE, request, String.class);
					logger.info("deleted userid: "+emailId);
					break;
				}
			}

			deleteOrg(token,orgGuiID);

			return response.getStatusCode();
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}


	private JSONArray parseResponse(ResponseEntity<String> response) throws Exception{

		JSONParser parser1 = new JSONParser();
		Object  obj1  = parser1.parse(response.getBody());
		JSONObject resultObj = (JSONObject) obj1;
		JSONArray array = (JSONArray) resultObj.get("resources");
		return  array;
	}

	public HttpStatus deleteSpace(String token, String spaceId) {
		String orgid=null;
		try
		{
			logger.info("deleting spaceid: "+spaceId);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "bearer "+token);
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			HttpEntity<String> request = new HttpEntity<String>(headers);
			SSLCertificateValidation.disable();
			logger.info("deleting spaceid url: "+uaacreatespace+"/"+spaceId);
			ResponseEntity<String> response = restTemplate.exchange(uaacreatespace+"/"+spaceId+"?recursive=true", HttpMethod.DELETE, request, String.class);
			logger.info("deleted spaceId: "+spaceId);
			return response.getStatusCode();
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}
}
