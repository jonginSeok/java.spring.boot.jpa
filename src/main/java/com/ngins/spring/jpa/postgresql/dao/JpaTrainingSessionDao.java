/**
 * 
 */
package com.ngins.spring.jpa.postgresql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngins.spring.jpa.postgresql.jpa.entity.Training_trainingsession;
import com.ngins.spring.jpa.postgresql.jpa.repository.TrainingsessionRepogitory;

/**
 * 
 */
@Component
public class JpaTrainingSessionDao {
	
	@Autowired
	private TrainingsessionRepogitory trainingsessionRepogitory;
	
	
	public Training_trainingsession getTrainingsessionById(long id ){
		return trainingsessionRepogitory.getReferenceById(id);
	}

}
