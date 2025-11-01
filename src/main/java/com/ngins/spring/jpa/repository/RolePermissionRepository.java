/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.RolePermission;
import com.ngins.spring.jpa.entity.postgresql.RolePermissionId;
/**
 * 
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {

}
