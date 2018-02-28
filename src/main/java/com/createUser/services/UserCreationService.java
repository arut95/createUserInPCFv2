package com.createUser.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.createUser.domain.UserEntity;
import com.createUser.repositories.UserRepository;

@Service
public class UserCreationService implements IUserCreationService {

	private UserRepository userRepository;
	 
	@Autowired
	public UserCreationService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	public List<UserEntity> listAll() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public UserEntity getById(Long id) {
		  return userRepository.findOne(id);
	}

	@Override
	public UserEntity saveOrUpdate(UserEntity user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(Long id) {
		 userRepository.delete(id);		
	}
	
	@Override
	public UserEntity findbyEmailId(String employeeemailid)
	{
		return userRepository.findByEmailId(employeeemailid);
	}
	
	@Override
	public List<UserEntity> getUserForActivation(String activationstatus,Integer numberofattempts)
	{
		return userRepository.getUserForActivation(activationstatus,numberofattempts);
	}
	
	@Override
	public void updateActStatus(String employeeemailid,String activationstatus,Date dt)
	{
		userRepository.updateAccountStatus(employeeemailid, activationstatus,dt);
	}
	
	@Override
	public void updateAccountCreationStatus(String employeeemailid,String activationstatus,Date dt)
	{
		userRepository.updateAccountCreationStatus(employeeemailid, activationstatus,dt);
	}
	
	@Override
	public void updateOrgID(String employeeemailid,String orgid,Date dt)
	{
		userRepository.updateOrgID(employeeemailid, orgid, dt);
	}
	
	@Override
	public void deleteActStatus(String activationstatus,Date date)
	{
		userRepository.deleteActStatus(activationstatus, date);
	}
	
	@Override
	public void updateNoofAttempts(String employeeemailid,Integer attempt,String activationstatus)
	{
		userRepository.updateNoofAttempts(employeeemailid, attempt,activationstatus); 
	}
	
	@Override
	public List<UserEntity> getDeletionUsers(Date dt,String activationstatus) {
		List<UserEntity> users = new ArrayList<UserEntity>();
		userRepository.getDeleteUserEntities(dt,activationstatus).forEach(users::add);
		return users;
	}
	
	@Override
	public List<UserEntity> getDeletionRemainder(Date dt,String activationstatus) {
		List<UserEntity> users = new ArrayList<UserEntity>();
		userRepository.getDeleteUserEntities(dt,activationstatus).forEach(users::add);
		return users;
	}

	
	@Override
	public Long getLongCount(Date toDate,Date fromDate) {
		  return userRepository.Count(toDate,fromDate);
	}
}
