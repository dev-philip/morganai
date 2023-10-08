package com.morganai.morganai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morganai.morganai.util.aiMain;


@RestController
@RequestMapping("api/ai")
public class AiController {
	
	//api to get input from frontend
	@GetMapping("{aiInput}")
	public ResponseEntity<String> getAiResponse(@PathVariable("aiInput") String aiInput) {
//		System.out.println(aiInput);
		String aiResponse = aiMain.getAiResponce("aiInput");
//		System.out.println(aiResponse);
		return new ResponseEntity<>(aiResponse, HttpStatus.OK);
	}

}
