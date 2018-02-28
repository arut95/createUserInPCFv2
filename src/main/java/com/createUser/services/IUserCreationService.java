package com.createUser.services;


import java.util.Date;
import java.util.List;

import com.createUser.domain.UserEntity;

public interface IUserCreationService {
	List<UserEntity> listAll();
	UserEntity getById(Long id);
	UserEntity saveOrUpdate(UserEntity user);
	void delete(Long id);
	UserEntity findbyEmailId(String employeeemailid);
	List<UserEntity> getUserForActivation(String activationstatus,Integer numberofattempts);
	void updateActStatus(String employeeemailid, String activationstatus,Date dt);
	void updateAccountCreationStatus(String employeeemailid, String activationstatus,Date dt);	
	void deleteActStatus(String activationstatus, Date date);	
	void updateNoofAttempts(String employeeemailid, Integer attempt,String activationstatus);
	void updateOrgID(String employeeemailid, String orgid, Date dt);
	List<UserEntity> getDeletionUsers(Date dt,String activationstatus);
	List<UserEntity> getDeletionRemainder(Date dt,String activationstatus);
	Long getLongCount(Date toDate,Date fromDate);
}
