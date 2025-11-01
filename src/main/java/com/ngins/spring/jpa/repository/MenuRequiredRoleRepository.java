/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.mariadb.MenuRequiredRole;
import com.ngins.spring.jpa.entity.mariadb.MenuRequiredRoleId;
/**
 * 
 */
@Repository
public interface MenuRequiredRoleRepository extends JpaRepository<MenuRequiredRole, MenuRequiredRoleId> {

}
