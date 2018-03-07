package com.createUser.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = -3254406057751181181L;
	
    private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private Long employeeid;
	
	private String employeeemailid;
	
	private String supervisoremailid;
	
	private String department;
	
	private String proglanguage;
	
	private String phonenumber;
	
	private String timezone;
	
	private String uniqueid;
	
	private String activationstatus;	

	private Date  activationdate;
	
	private Date  creationdate;	
	
	private Date  lastmodifieddate;
	
	private Integer numberofattempts;	
	
	private String  orgid;
	
	private String  proficiencylevel;
	
	private String  frameworks;
	
	private String  linuxskill;
	
	private String  fullstackcompleted;
	
	private String  fullstackskills;
	
	private String  userthoughtstraning;
	
	private String  usercommentstraining;
	
	public String getProficiencylevel() {
		return proficiencylevel;
	}

	public void setProficiencylevel(String proficiencylevel) {
		this.proficiencylevel = proficiencylevel;
	}

	public String getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(String frameworks) {
		this.frameworks = frameworks;
	}

	public String getLinuxskill() {
		return linuxskill;
	}

	public void setLinuxskill(String linuxskill) {
		this.linuxskill = linuxskill;
	}

	public String getFullstackcompleted() {
		return fullstackcompleted;
	}

	public void setFullstackcompleted(String fullstackcompleted) {
		this.fullstackcompleted = fullstackcompleted;
	}

	public String getFullstackskills() {
		return fullstackskills;
	}

	public void setFullstackskills(String fullstackskills) {
		this.fullstackskills = fullstackskills;
	}

	public String getUserthoughtstraning() {
		return userthoughtstraning;
	}

	public void setUserthoughtstraning(String userthoughtstraning) {
		this.userthoughtstraning = userthoughtstraning;
	}

	public String getUsercommentstraining() {
		return usercommentstraining;
	}

	public void setUsercommentstraining(String usercommentstraining) {
		this.usercommentstraining = usercommentstraining;
	}	
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeeemailid() {
		return employeeemailid;
	}

	public void setEmployeeemailid(String employeeemailid) {
		this.employeeemailid = employeeemailid;
	}

	public String getSupervisoremailid() {
		return supervisoremailid;
	}

	public void setSupervisoremailid(String supervisoremailid) {
		this.supervisoremailid = supervisoremailid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProglanguage() {
		return proglanguage;
	}

	public void setProglanguage(String proglanguage) {
		this.proglanguage = proglanguage;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getActivationstatus() {
		return activationstatus;
	}

	public void setActivationstatus(String activationstatus) {
		this.activationstatus = activationstatus;
	}

	public Date getActivationdate() {
		return activationdate;
	}

	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
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

	public Integer getNumberofattempts() {
		return numberofattempts;
	}

	public void setNumberofattempts(Integer numberofattempts) {
		this.numberofattempts = numberofattempts;
	}	 
}
