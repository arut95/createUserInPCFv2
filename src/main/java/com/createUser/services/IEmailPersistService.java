package com.createUser.services;

import java.util.Date;
import java.util.List;

import com.createUser.domain.EmailEntity;

public interface IEmailPersistService {
	List<EmailEntity> listAll();	
	EmailEntity saveOrUpdate(EmailEntity email);
	void updateEmailStatus(String emailid,String activationstatus,Date dt);

}
