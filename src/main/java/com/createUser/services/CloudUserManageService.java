package com.createUser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.createUser.cloudAPI.ManageUsers;
import com.createUser.domain.UAAUserData;

@Service
public class CloudUserManageService implements ICloudUserManageService {

	@Autowired
	RestTemplate restTemplate;	
	
	@Autowired
	private ManageUsers manageUsers;
	
	public CloudUserManageService(ManageUsers manageUsers)
	{
		this.manageUsers = manageUsers;
	}
	
	@Override
	public String getToken(String credentials) {
		return manageUsers.getToken(credentials);
	}
	
	@Override
	public String createOrg(String token,String OrgName)
	{
		return manageUsers.createOrg(token,OrgName);
	}
	
//////////////////	
	@Override
	public String deleteUsersAndOrgs(String token)
	{
		return manageUsers.deleteUsersAndOrgs(token);
	}
	
	@Override
	public HttpStatus deleteOrg(String token,String orgId)
	{
		return manageUsers.deleteOrg(token,orgId);
	}
	
	@Override
	public String createSpace(String token,String spaceName,String orgId)
	{
		return manageUsers.createSpace(token, spaceName, orgId);
	}
	
	@Override
	public HttpStatus associateOrgtoUser(String token,String emailId,String orgId)
	{
		return manageUsers.associateOrgtoUser(token, emailId, orgId);	
	}

	@Override
	public HttpStatus associateSpacetoUser(String token,String emailId,String spaceId)
	{
		return manageUsers.associateSpacetoUser(token, emailId, spaceId);	
	}
	
	@Override
	public String createUser(String token,UAAUserData uaaUserData)
	{	
		return manageUsers.createUser(token, uaaUserData);
	}
	
	@Override
	public String getAuthtokenforOrgCreation(String username,String password, String grant_type)
	{	
		return manageUsers.getAuthToken(username, password, grant_type);
	}


	@Override
	public String getAuthToken(String credentials) {
		return manageUsers.getAuthToken("admin","great!23","password");
	}


	@Override
	public HttpStatus deleteSpace(String token, String spaceId) {
		return manageUsers.deleteSpace(token,spaceId);
	}


	@Override
	public HttpStatus deleteOperations(String authtoken,String orgGuiID,String userEmailId) {

		return manageUsers.deleteOperations(authtoken,orgGuiID,userEmailId);
		//return manageUsers.DeleteOperations("eyJhbGciOiJSUzI1NiIsImtpZCI6ImtleS0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiI1YjRhZWNmZDdjOGY0YmRhOTBkNGE2ZjYwMDlmM2U4MiIsInN1YiI6IjJjMWQ3YWJiLWFmMDMtNGJjMS04NmZjLTNlNDRkZjUzOGFhOSIsInNjb3BlIjpbImNsb3VkX2NvbnRyb2xsZXIuYWRtaW4iLCJyb3V0aW5nLnJvdXRlcl9ncm91cHMucmVhZCIsImNsb3VkX2NvbnRyb2xsZXIud3JpdGUiLCJuZXR3b3JrLmFkbWluIiwiZG9wcGxlci5maXJlaG9zZSIsIm9wZW5pZCIsInJvdXRpbmcucm91dGVyX2dyb3Vwcy53cml0ZSIsInNjaW0ucmVhZCIsInVhYS51c2VyIiwiY2xvdWRfY29udHJvbGxlci5yZWFkIiwicGFzc3dvcmQud3JpdGUiLCJzY2ltLndyaXRlIl0sImNsaWVudF9pZCI6ImNmIiwiY2lkIjoiY2YiLCJhenAiOiJjZiIsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIyYzFkN2FiYi1hZjAzLTRiYzEtODZmYy0zZTQ0ZGY1MzhhYTkiLCJvcmlnaW4iOiJ1YWEiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImVtYWlsIjoiYWRtaW4iLCJhdXRoX3RpbWUiOjE1MDc3MjExMjYsInJldl9zaWciOiIzZGVlMjJkMyIsImlhdCI6MTUwNzcyMTEyNiwiZXhwIjoxNTA3NzI4MzI2LCJpc3MiOiJodHRwczovL3VhYS5zeXN0ZW0uZGV2LmRpZ2lmYWJyaWNwY2YuY29tL29hdXRoL3Rva2VuIiwiemlkIjoidWFhIiwiYXVkIjpbImNsb3VkX2NvbnRyb2xsZXIiLCJzY2ltIiwicGFzc3dvcmQiLCJjZiIsInVhYSIsIm9wZW5pZCIsImRvcHBsZXIiLCJyb3V0aW5nLnJvdXRlcl9ncm91cHMiLCJuZXR3b3JrIl19.q2-4ImI-EkoljqdE_yjR8QMPreKQ-Leo6LlyWeCD0EWwPYI0O_ixcmNPZKtthS0HlXMeH_pD-xZiQXzgSP7wF0hEpeCVX4sQ7vZV6FaLAL0W6hVPFlERBCfKb6k58xmFMTf_dlBRtLy4rbYD7NfPogqdKVxN8f4G-t17-hxpIxTfIOZqe0eWbCLlROU-yZyfvgftwLfF4biJc4FvPCqF95X_3ZUXrBZuu_t0q6vUUmBjY1bRdq2eNOMJ1MU1pDzhDeIHjZCxmpxu4y4KSkz0nVnh7lBpDh5STveR1Izyy5c5VYVrQY8_q1Gsqe32KPGrqFqM5R5F-mee9orNrIT83Q",
		//		orgGuiID,"");
	}
}
