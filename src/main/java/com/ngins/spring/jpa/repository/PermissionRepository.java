/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.mariadb.Permission;
/**
 * 
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
