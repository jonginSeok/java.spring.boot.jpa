/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.mariadb.MenuRequiredPermission;
import com.ngins.spring.jpa.entity.mariadb.MenuRequiredPermissionId;

/**
 * 
 */
@Repository
public interface MenuRequiredPermissionRepository extends JpaRepository<MenuRequiredPermission, MenuRequiredPermissionId> {

}
