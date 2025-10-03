/**
 * 
 */
package com.ngins.spring.jpa.postgresql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngins.spring.jpa.postgresql.jpa.entity.AppUser;
import com.ngins.spring.jpa.postgresql.jpa.repository.AppUserRepogitory;

/**
 * 
 */
@Component
public class JpaTrainingSessionDao {

	@Autowired
	private AppUserRepogitory appUserRepogitory;

	public AppUser getAppUserById(long id) {
		return appUserRepogitory.getReferenceById(id);
	}

}
