/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.MenuRequiredRole;
import com.ngins.spring.jpa.entity.postgresql.MenuRequiredRoleId;
/**
 * 
 */
@Repository
public interface MenuRequiredRoleRepository extends JpaRepository<MenuRequiredRole, MenuRequiredRoleId> {

}
