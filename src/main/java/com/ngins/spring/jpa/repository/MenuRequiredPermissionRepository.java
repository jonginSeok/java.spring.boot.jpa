/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.MenuRequiredPermission;
import com.ngins.spring.jpa.entity.postgresql.MenuRequiredPermissionId;

/**
 * 
 */
@Repository
public interface MenuRequiredPermissionRepository extends JpaRepository<MenuRequiredPermission, MenuRequiredPermissionId> {

}
