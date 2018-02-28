package com.createUser.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="emailentity")

public class EmailEntity implements Serializable{
	
	private static final long serialVersionUID = -3254406057751181181L;
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name = "receipient")
	private String receipient;
	
	@Column(name = "hcmreceipient")
	private String hcmreceipient;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "status")
	private String status; 
	
	@Column(name = "creationdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date  creationdate;	
	
	@Column(name = "lastmodifieddate", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  lastmodifieddate;
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "password")
	private String password; 
	
	@Column(name = "template")
	private String template; 
	
	@Column(name = "track")
	private String track; 
	
	@Column(name = "learningurl")
	private String learningurl; 
	
	@Column(name = "uniqueid")
	private String uniqueid; 
	
	@Column(name = "message")
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
