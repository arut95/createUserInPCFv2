package com.palusers.domain;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import com.createUser.domain.EmailEntity;

public class EmailEntityTest {

	@Test
    public void test() {
		LocalDateTime dt = LocalDateTime.now();
		EmailEntity emailEntity = new EmailEntity();        
        emailEntity.setCreationdate(Timestamp.valueOf(dt));
        assertEquals(emailEntity.getCreationdate(),Timestamp.valueOf(dt));
        
        emailEntity.setHcmreceipient("hcmreceipient");;
        assertEquals(emailEntity.getHcmreceipient(),"hcmreceipient");
        
        emailEntity.setId(1L);
        assertEquals((Long)emailEntity.getId(),(Long)1L);
        
        emailEntity.setLastmodifieddate(Timestamp.valueOf(dt));
        assertEquals(emailEntity.getLastmodifieddate(),Timestamp.valueOf(dt));
        
        emailEntity.setLearningurl("learningurl");
        assertEquals(emailEntity.getLearningurl(),"learningurl");
        
        emailEntity.setMessage("message");
        assertEquals(emailEntity.getMessage(),"message");
        
        emailEntity.setPassword("password");
        assertEquals(emailEntity.getPassword(),"password");
        
        emailEntity.setReceipient("receipient");
        assertEquals(emailEntity.getReceipient(),"receipient");
        
        emailEntity.setStatus("status");
        assertEquals(emailEntity.getStatus(),"status");
        
        emailEntity.setSubject("subject");
        assertEquals(emailEntity.getSubject(),"subject");
        
        emailEntity.setTemplate("template");
        assertEquals(emailEntity.getTemplate(),"template");
        
        emailEntity.setTrack("track");
        assertEquals(emailEntity.getTrack(),"track");
        
        emailEntity.setUniqueid("uniqueid");
        assertEquals(emailEntity.getUniqueid(),"uniqueid");
        
        emailEntity.setUsername("username");
        assertEquals(emailEntity.getUsername(),"username");
    }
}
