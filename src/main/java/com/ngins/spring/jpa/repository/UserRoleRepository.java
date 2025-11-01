/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.UserRole;
import com.ngins.spring.jpa.entity.postgresql.UserRoleId;
/**
 * 
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

}
