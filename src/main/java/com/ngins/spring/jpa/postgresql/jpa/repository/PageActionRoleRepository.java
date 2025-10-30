package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.PageActionRole;
import com.ngins.spring.jpa.postgresql.jpa.entity.PageActionRoleId;
/**
 * 
 */
@Repository
public interface PageActionRoleRepository extends JpaRepository<PageActionRole, PageActionRoleId> {

}
