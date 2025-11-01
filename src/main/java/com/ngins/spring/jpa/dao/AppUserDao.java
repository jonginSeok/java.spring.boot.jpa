/**
 * 
 */
package com.ngins.spring.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngins.spring.jpa.entity.mariadb.AppUser;
import com.ngins.spring.jpa.repository.AppUserRepository;

/**
 * 
 */
@Component
public class AppUserDao {

	@Autowired
	private AppUserRepository appUserRepogitory;

	public AppUser getAppUserById(long id) {
		return appUserRepogitory.getReferenceById(id);
	}

}
