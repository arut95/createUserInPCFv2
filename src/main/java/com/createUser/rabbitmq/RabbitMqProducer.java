package com.createUser.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RabbitMqProducer {
	
	private final RabbitTemplate template;

    @Autowired
    public RabbitMqProducer(RabbitTemplate template) {
        this.template = template;
    }    
    
    @Scheduled(fixedRate = 10000)
    public void sendJSONMessage() {
    		UserMailEntity userInfo = new UserMailEntity();
	    userInfo.setApiEndpointURL("digifabricpcf.com");
	    userInfo.setAppsManagerURL("AppsManager");
	    userInfo.setOrgName("enablement");
	    userInfo.setPassword("dbsa24");
	    userInfo.setSpaceName("sandbox");
	    userInfo.setSubject("Account Creation for PCF");
	    userInfo.setUsername("Arutsudar.A@cognizant.com");
	    this.template.convertAndSend("usermail-queue", userInfo);
		System.out.println("Send msg = " + userInfo);
    }
}

