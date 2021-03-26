package com.example.mqspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.mqspring.exceptions.AppException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MQService {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendHelloWorld() {
		
		final Logger LOG = LoggerFactory.getLogger(MQService.class);

		try {
			String helloWorld = "Hello World!";
			jmsTemplate.convertAndSend("DEV.QUEUE.1", helloWorld);
			LOG.debug("Successfully Sent message: {} to the queue", helloWorld);
		} catch (JmsException ex) {
			throw new AppException("MQAPP001", "Error sending message to the queue.", ex);
		}
	}
	
	public String receiveMessage() {
	    try{
	        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
	    }catch(JmsException ex) {
	    	throw new AppException("MQAPP002", "Error receiving message from the queue.", ex);
	    }
	}
	
	public String sendJson(Map<String,Object> requestMap) {
		
		String jsonResult = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter()
			  .writeValueAsString(requestMap);
		    jmsTemplate.convertAndSend("DEV.QUEUE.1", jsonResult);
		} catch (JsonProcessingException e) {
			throw new AppException("MQAPP003", "Error processing json request.", e);
		} catch(JmsException ex) {
			throw new AppException("MQAPP001", "Error sending message to the queue.", ex);
	    }
		
		return jsonResult;		
	}		

}
