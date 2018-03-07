package com.createUser.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EmailEntity implements Serializable{
	
	private static final long serialVersionUID = -3254406057751181181L;
	
    private Long id;
	
	private String receipient;
	
	private String hcmreceipient;
	
	private String subject;
	
	private String status; 
	
	private Date  creationdate;	
	
	private Date  lastmodifieddate;
	
	private String username; 
	
	private String password; 
	
	private String template; 
	
	private String track; 
	
	private String learningurl; 
	
	private String uniqueid; 
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getLearningurl() {
		return learningurl;
	}

	public void setLearningurl(String learningurl) {
		this.learningurl = learningurl;
	}
	
	public String getUsername() {
		return username;
	}

	public String getHcmreceipient() {
		return hcmreceipient;
	}

	public void setHcmreceipient(String hcmreceipient) {
		this.hcmreceipient = hcmreceipient;
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
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReceipient() {
		return receipient;
	}

	public void setReceipient(String receipient) {
		this.receipient = receipient;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getLastmodifieddate() {
		return lastmodifieddate;
	}

	public void setLastmodifieddate(Date lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}

	
}
