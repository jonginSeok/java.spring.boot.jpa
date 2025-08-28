/**
 * 
 */
package com.ngins.spring.jpa.postgresql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngins.spring.jpa.postgresql.jpa.entity.Trainingsession;
import com.ngins.spring.jpa.postgresql.jpa.repository.TrainingsessionRepogitory;

/**
 * 
 */
@Component
public class JpaTrainingSessionDao {
	
	@Autowired
	private TrainingsessionRepogitory trainingsessionRepogitory;
	
	
	public Trainingsession getTrainingsessionById(long id ){
		return trainingsessionRepogitory.getReferenceById(id);
	}

}
