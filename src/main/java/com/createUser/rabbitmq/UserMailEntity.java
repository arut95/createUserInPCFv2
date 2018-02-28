package com.createUser.rabbitmq;

import org.json.JSONException;
import org.json.JSONObject;

public class UserMailEntity {

	private String username;
	private String password;
	private String orgName;
	private String spaceName;
	private String subject;
	private String apiEndpointURL;
	private String appsManagerURL;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getApiEndpointURL() {
		return apiEndpointURL;
	}

	public void setApiEndpointURL(String apiEndpointURL) {
		this.apiEndpointURL = apiEndpointURL;
	}

	public String getAppsManagerURL() {
		return appsManagerURL;
	}

	public void setAppsManagerURL(String appsManagerURL) {
		this.appsManagerURL = appsManagerURL;
	}

	public String toString() {
		JSONObject jsonInfo = new JSONObject();

		try {
			jsonInfo.put("username", this.username);
			jsonInfo.put("password", this.password);
			jsonInfo.put("orgName", this.orgName);
			jsonInfo.put("spaceName", this.spaceName);
			jsonInfo.put("subject", this.subject);
			jsonInfo.put("apiEndpointURL", this.apiEndpointURL);
			jsonInfo.put("appsManagerURL", this.appsManagerURL);

		} catch (JSONException e1) {
		}
		return jsonInfo.toString();
	}

}
