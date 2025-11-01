package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.PageActionRole;
import com.ngins.spring.jpa.entity.postgresql.PageActionRoleId;
/**
 * 
 */
@Repository
public interface PageActionRoleRepository extends JpaRepository<PageActionRole, PageActionRoleId> {

}
