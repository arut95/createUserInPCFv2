package com.createUser.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.createUser.common.ApplicationConstants;
import com.createUser.domain.EmailEntity;
import com.createUser.domain.EmailRequest;

@Service
public class EmailCreation implements IEmailCreation {

	private EmailPersistService emailPersistService;
	
    @Autowired
    public EmailCreation(EmailPersistService emailPersistService) {
        this.emailPersistService = emailPersistService;
    }
    
    @Override
    public boolean sendRegistrationEmail(EmailRequest emailData) throws MessagingException {
        return saveMail(emailData,ApplicationConstants.EmailTemplte.REGISTRATIONEMAIL.name());
    }
    
    @Override
    public boolean sendAccountEmail(EmailRequest emailData) throws MessagingException {
    	return saveMail(emailData, ApplicationConstants.EmailTemplte.ACCOUNTEMAIL.name());
    }
    
    @Override
    public boolean sendErrorMail(EmailRequest emailData) throws MessagingException {    	
    	return saveMail(emailData,ApplicationConstants.EmailTemplte.ERROREMAIL.name());
    }
    
    @Override
    public boolean sendSupportTeamMail(EmailRequest emailData) throws MessagingException {    	
    	return saveMail(emailData,ApplicationConstants.EmailTemplte.SUPPORTTEAMEMAIL.name());
    }
    
    @Override
	public boolean sendDeleteRemainderMail(EmailRequest emailData) throws MessagingException {
		return saveMail(emailData,ApplicationConstants.EmailTemplte.DELETEREMAINDEREMAIL.name());
	}

	@Override
	public boolean sendDeleteAccountMail(EmailRequest emailData) throws MessagingException {
		return saveMail(emailData,ApplicationConstants.EmailTemplte.DELETEACCOUNTEMAIL.name());
	}
	
	@Override
    public boolean sendLimitEmail(EmailRequest emailData) throws MessagingException {
        return saveMail(emailData,ApplicationConstants.EmailTemplte.LIMITEMAIL.name());
    }
	
	private boolean saveMail(EmailRequest emailData,String emailTemplate)  {
		EmailEntity email = new EmailEntity();
		email.setReceipient(emailData.Recipient);
		email.setUsername(emailData.Username);
		email.setHcmreceipient(emailData.HCMRecipient);
		email.setPassword(emailData.Password);		
		email.setCreationdate(Timestamp.valueOf(LocalDateTime.now()));
		email.setSubject(emailData.Subject);
		email.setTemplate(emailTemplate);
		email.setTrack(emailData.Track);
		email.setLearningurl(emailData.LearningUrl);
		email.setUniqueid(emailData.UniqueId);
		email.setTemplate(emailTemplate);
		email.setStatus(ApplicationConstants.EMAILSTATUS.NOTSENT.name());
		email.setMessage(emailData.Message);
		emailPersistService.saveOrUpdate(email);
		return true;
    }

	
}
