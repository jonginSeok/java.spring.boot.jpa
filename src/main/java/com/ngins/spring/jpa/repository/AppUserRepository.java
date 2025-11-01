/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.mariadb.AppUser;

/**
 * 
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
