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
@Table(name="userentity")

public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = -3254406057751181181L;
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name = "firstname", length =50)
	private String firstname;
	
	@Column(name = "lastname",length =50)
	private String lastname;
	
	@Column(name = "employeeid")
	private Long employeeid;
	
	@Column(name = "employeeemailid",length =50)
	private String employeeemailid;
	
	@Column(name = "supervisoremailid",length =50)
	private String supervisoremailid;
	
	@Column(name = "department",length =50)
	private String department;
	
	@Column(name = "proglanguage")
	private String proglanguage;
	
	@Column(name = "phonenumber",length =15)
	private String phonenumber;
	
	@Column(name = "timezone",length =50)
	private String timezone;
	
	@Column(name = "uniqueid")
	private String uniqueid;
	
	@Column(name = "activationstatus")
	private String activationstatus;	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "activationdate", nullable = true)
	private Date  activationdate;
	
	@Column(name = "creationdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date  creationdate;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastmodifieddate", nullable = true)
	private Date  lastmodifieddate;
	
	@Column(name = "numberofattempts")
	private Integer numberofattempts;	
	
	@Column(name = "orgid")
	private String  orgid;
	
	@Column(name = "proficiencylevel")
	private String  proficiencylevel;
	
	@Column(name = "frameworks")
	private String  frameworks;
	
	@Column(name = "linuxskill")
	private String  linuxskill;
	
	@Column(name = "fullstackcompleted")
	private String  fullstackcompleted;
	
	@Column(name = "fullstackskills")
	private String  fullstackskills;
	
	@Column(name = "userthoughtstraning")
	private String  userthoughtstraning;
	
	@Column(name = "usercommentstraining")
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
