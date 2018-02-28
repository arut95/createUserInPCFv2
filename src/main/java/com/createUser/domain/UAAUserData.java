package com.createUser.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"externalId",
"userName",
"emails",
"active",
"verified",
"origin",
"password",
"schemas"
})
public class UAAUserData {

	@JsonProperty("externalId")
	public String externalId;
	@JsonProperty("userName")
	public String userName;
	@JsonProperty("emails")
	public List<UAAEmailData> emails = null;
	@JsonProperty("active")
	public Boolean active;
	@JsonProperty("verified")
	public Boolean verified;
	@JsonProperty("origin")
	public String origin;
	@JsonProperty("password")
	public String password;
	@JsonProperty("schemas")
	public List<String> schemas = null;
}