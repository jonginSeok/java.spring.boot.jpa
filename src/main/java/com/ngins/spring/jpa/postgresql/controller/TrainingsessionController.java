/**
 * 
 */
package com.ngins.spring.jpa.postgresql.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<List<Trainingsession>> getAllTrainingsessions(@RequestParam(required = false) Long id) {
		try {
			List<Trainingsession> trainingsessions;

			if (id == null) {
				trainingsessions = trainingsessionRepogitory.findAll();
			} else {
				Optional<Trainingsession> trainingsession = trainingsessionRepogitory.findById(id);
				trainingsessions = trainingsession.map(Collections::singletonList) // 값이 있으면 리스트로 감싸기
						.orElse(Collections.emptyList()); // 없으면 빈 리스트 반환
			}

			return ResponseEntity.ok(trainingsessions);
			// return new ResponseEntity<>(trainingsessions, HttpStatus.OK);

		} catch (Exception e) {
			// return new ResponseEntity<List<Trainingsession>>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}

}
