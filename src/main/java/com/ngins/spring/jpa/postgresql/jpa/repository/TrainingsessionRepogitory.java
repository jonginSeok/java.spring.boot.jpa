/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.Training_trainingsession;

/**
 * 
 */
@Repository
public interface TrainingsessionRepogitory extends JpaRepository<Training_trainingsession, Long>{
	
}
