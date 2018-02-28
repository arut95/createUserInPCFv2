package com.createUser.services;

import javax.mail.MessagingException;

import com.createUser.domain.EmailRequest;

public interface IEmailCreation {

	boolean sendAccountEmail(EmailRequest emailData) throws MessagingException;

	boolean sendErrorMail(EmailRequest emailData) throws MessagingException;

	boolean sendRegistrationEmail(EmailRequest emailData) throws MessagingException;

	boolean sendSupportTeamMail(EmailRequest emailData) throws MessagingException;

	boolean sendDeleteRemainderMail(EmailRequest emailData) throws MessagingException;
	
	boolean sendDeleteAccountMail(EmailRequest emailData) throws MessagingException;

	boolean sendLimitEmail(EmailRequest emailData) throws MessagingException;
	
}
