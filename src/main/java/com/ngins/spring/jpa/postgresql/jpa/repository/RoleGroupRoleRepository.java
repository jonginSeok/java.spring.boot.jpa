/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.RoleGroupRole;
import com.ngins.spring.jpa.postgresql.jpa.entity.RoleGroupRoleId;
/**
 * 
 */
@Repository
public interface RoleGroupRoleRepository extends JpaRepository<RoleGroupRole, RoleGroupRoleId> {

}
