/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.RoleGroup;
/**
 * 
 */
@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long> {

}
