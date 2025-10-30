/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.PageActionPermission;
import com.ngins.spring.jpa.postgresql.jpa.entity.PageActionPermissionId;
/**
 * 
 */
@Repository
public interface PageActionPermissionRepositiry extends JpaRepository<PageActionPermission, PageActionPermissionId> {

}
