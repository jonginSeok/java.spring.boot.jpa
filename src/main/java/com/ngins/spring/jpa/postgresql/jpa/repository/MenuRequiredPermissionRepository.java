/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.MenuRequiredPermission;
import com.ngins.spring.jpa.postgresql.jpa.entity.MenuRequiredPermissionId;

/**
 * 
 */
@Repository
public interface MenuRequiredPermissionRepository extends JpaRepository<MenuRequiredPermission, MenuRequiredPermissionId> {

}
