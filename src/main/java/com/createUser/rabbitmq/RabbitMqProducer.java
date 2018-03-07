package com.createUser.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqProducer {
	
	private final RabbitTemplate template;

    @Autowired
    public RabbitMqProducer(RabbitTemplate template) {
        this.template = template;
    }    
    
    
    public void sendJSONMessage(UserMailEntity userInfo) {
	    this.template.convertAndSend("usermail-queue", userInfo);
		System.out.println("Send msg = " + userInfo);
    }
}

