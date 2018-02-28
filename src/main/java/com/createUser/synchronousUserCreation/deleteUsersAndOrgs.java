package com.createUser.synchronousUserCreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.createUser.cloudAPI.ManageUsers;
import com.createUser.common.ApplicationConstants;
import com.createUser.services.CloudUserManageService;

@RestController
public class deleteUsersAndOrgs {

	@Autowired
	private ManageUsers manageUsers;
	
	@Autowired
	private CloudUserManageService cloudUsrManageService;

	@Value("${oauthpassword}")
	private String oauthpassword;
	
	@Value("${oauthusername}")
	private String oauthusername;
	
	@Value("${uaalogin}")
	private String uaalogin;
	
	@Value("${fixedDelayDeleteAccountAndOrgInMilliseconds}")
	private String fixedDelayDeleteAccountAndOrgInMilliseconds;

	@Scheduled(fixedDelayString = "${fixedDelayDeleteAccountAndOrgInMilliseconds}")
	public void delete_UsersAndOrgs() {
		String oAuthtoken = cloudUsrManageService.getAuthtokenforOrgCreation(oauthusername,oauthpassword,ApplicationConstants.OAUTHGRANTTYPE);
		if(oAuthtoken !=null)
			cloudUsrManageService.deleteUsersAndOrgs(oAuthtoken);
	}
}
