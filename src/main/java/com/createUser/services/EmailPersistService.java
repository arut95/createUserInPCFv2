package com.createUser.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.createUser.domain.EmailEntity;
import com.createUser.repositories.EmailRepository;

@Service
public class EmailPersistService implements IEmailPersistService {

	private EmailRepository emailRepository;
	 
	@Autowired
	public EmailPersistService(EmailRepository emailRepository)
	{
		this.emailRepository = emailRepository;
	}
	
	@Override
	public List<EmailEntity> listAll() {
		List<EmailEntity> emails = new ArrayList<EmailEntity>();
		emailRepository.findAll().forEach(emails::add);
		return emails;
	}
	
	@Override
	public EmailEntity saveOrUpdate(EmailEntity email) {
		emailRepository.save(email);
		return email;
	}

	@Override
	public void updateEmailStatus(String emailid, String activationstatus, Date dt) {
		emailRepository.updateEmailStatus(emailid, activationstatus, dt);		
	}

	
}
