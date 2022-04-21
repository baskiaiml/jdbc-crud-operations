/**
 * 
 */
package com.stackroute.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 91994
 *
 */
@RestController
@RequestMapping("/")
public class SpringExampleController {

	@ResponseBody
	@GetMapping("/healthcheck")
	public ResponseEntity<Object> healthCheck() {
		
		return new ResponseEntity<Object>("{\"status\":\"Spring Example Service is working\"}", HttpStatus.ACCEPTED);
	}
 
}
