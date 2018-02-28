package com.createUser.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"value",
"primary"
})
public class UAAEmailData {

	@JsonProperty("value")
	public String value;
	@JsonProperty("primary")
	public Boolean primary;
}