/**
 * 
 */
package com.ngins.spring.jpa.postgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ngins.spring.jpa.postgresql.jpa.entity.Trainingsession;
import com.ngins.spring.jpa.postgresql.jpa.repository.TrainingsessionRepogitory;

/**
 * 
 */
@RestController
public class TrainingsessionController {

	@Autowired
	TrainingsessionRepogitory trainingsessionRepogitory;

	@GetMapping("/training")
	public ResponseEntity<List<Trainingsession>> getAllTrainingsessions(@RequestParam(required = false) String name) {
		try {
			List<Trainingsession> trainingsessions = trainingsessionRepogitory.findAll();
			return new ResponseEntity<>(trainingsessions, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<Trainingsession>>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// [Err] The constructor ResponseEntity<List<Trainingsession>>(List<Trainingsession>, HttpStatusCode) is ambiguous
		}
	}

}
