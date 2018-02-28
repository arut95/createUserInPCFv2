package com.createUser.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.createUser.domain.EmailEntity;

public interface EmailRepository extends CrudRepository<EmailEntity, Long>  {
	
	
	@Query("select email from EmailEntity email where email.status =?1")
	List<EmailEntity> getEmailEntities(String status);	
	
	@Transactional 
	@Modifying
	@Query("Update EmailEntity email set email.status =?2, email.lastmodifieddate= ?3 where email.receipient =?1")
	void updateEmailStatus(String emailid,String status,Date dt);	
	
	
}
