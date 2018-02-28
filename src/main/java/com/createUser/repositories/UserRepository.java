package com.createUser.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.createUser.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>  {

	@Query("select user from UserEntity  user where user.employeeemailid =?1")	
	UserEntity findByEmailId(String employeeemailid);
	
	@Query("select user from UserEntity user where user.activationstatus =?1 and user.numberofattempts < ?2")
	List<UserEntity> getUserForActivation(String activationstatus, Integer numberofattempts);	
		
	@Query("select count(user) from UserEntity user where user.creationdate between ?1 and ?2")	
	Long Count(Date toDate,Date fromDate);
	
	@Transactional 
	@Modifying
	@Query("Update UserEntity user set user.activationstatus =?2, user.lastmodifieddate= ?3 where user.employeeemailid =?1")
	void updateAccountStatus(String employeeemailid,String activationstatus,Date dt);
	
	@Transactional 
	@Modifying
	@Query("Update UserEntity user set user.orgid =?2, user.lastmodifieddate= ?3 where user.employeeemailid =?1")
	void updateOrgID(String employeeemailid,String orgid,Date dt);
	
	@Transactional 
	@Modifying
	@Query("Update UserEntity user set user.activationstatus =?2, user.activationdate= ?3,user.lastmodifieddate= ?3 where user.employeeemailid =?1")
	void updateAccountCreationStatus(String employeeemailid,String activationstatus,Date dt);
	
	@Transactional 
	@Modifying
	@Query("Update UserEntity user set user.numberofattempts =?2, user.activationstatus= ?3 where user.employeeemailid =?1")
	void updateNoofAttempts(String employeeemailid,Integer attempt,String activationstatus);
	
	@Transactional 
	@Modifying
	@Query("Update UserEntity user set user.activationstatus =?1 where user.activationdate <= ?2)")
	void deleteActStatus(String activationstatus,Date date);
	
	@Query("select user from UserEntity user where user.activationdate <=?1 and user.activationstatus= ?2")
	List<UserEntity> getDeleteUserEntities(Date dt,String activationstatus);
	
}
