package com.createUser.services;

import org.springframework.http.HttpStatus;

import com.createUser.domain.UAAUserData;

public interface ICloudUserManageService {	
	public String createUser(String token,UAAUserData uaaUserData);
	public String createOrg(String token,String OrgName);
	public String getAuthtokenforOrgCreation(String username, String password, String grant_type);
	public HttpStatus associateOrgtoUser(String token, String emailId, String orgId);
	String createSpace(String token, String spaceName, String orgId);
	public HttpStatus associateSpacetoUser(String token, String emailId, String spaceId);
	HttpStatus deleteOrg(String token, String orgId);
	String getToken(String credentials);
	String getAuthToken(String credentials);
	HttpStatus deleteSpace(String token, String spaceId);
	HttpStatus deleteOperations(String oAuthToken,String orgId, String userEmailId);
	
//
	public String deleteUsersAndOrgs(String token);
	
}
