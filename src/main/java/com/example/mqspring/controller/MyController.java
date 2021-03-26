package com.example.mqspring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mqspring.service.MQService;

@RestController
@EnableJms
public class MyController {
	
	@Autowired
	private MQService mqService;
	
	public MyController(MQService mqService) {
		this.mqService = mqService;
	}
	
	@GetMapping("/api/send-hello-world")
	String send(){
		mqService.sendHelloWorld();
		return "OK";
	}
	
	@GetMapping("/api/recv")
	String recv(){
	    return mqService.receiveMessage();
	}
	
	@PostMapping("/api/send-json")
	String sendPost(@RequestBody Map<String,Object> requestMap) {
       return mqService.sendJson(requestMap);
	}
}
