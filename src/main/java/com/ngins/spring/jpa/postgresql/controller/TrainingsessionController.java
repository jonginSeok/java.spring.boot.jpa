/**
 * 
 */
package com.ngins.spring.jpa.postgresql.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ngins.spring.jpa.postgresql.jpa.entity.AppUser;
import com.ngins.spring.jpa.postgresql.jpa.repository.AppUserRepogitory;

/**
 * 
 */

@RestController
public class TrainingsessionController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingsessionController.class);

	@Autowired
	AppUserRepogitory trainingsessionRepogitory;

	@GetMapping("/training/trainingsession")
	public ResponseEntity<List<AppUser>> getAllTrainingsessions(@RequestParam(required = false) Long id) {

		List<AppUser> trainingsessions;

		try {
			logger.info("데이터 id:{}", id);
			if (id == null) {
				trainingsessions = trainingsessionRepogitory.findAll();

			} else {
				Optional<AppUser> trainingsession = trainingsessionRepogitory.findById(id);
				trainingsessions = trainingsession.map(Collections::singletonList) // 값이 있으면 리스트로 감싸기
						.orElse(Collections.emptyList()); // 없으면 빈 리스트 반환
			}

			logger.info("데이터 사이즈:{}", trainingsessions.size());

			return ResponseEntity.ok(trainingsessions);
			// return new ResponseEntity<>(trainingsessions, HttpStatus.OK);

		} catch (Exception e) {
			// return new ResponseEntity<List<Trainingsession>>(null,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

}
