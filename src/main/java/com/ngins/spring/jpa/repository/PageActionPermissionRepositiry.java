/**
 * 
 */
package com.ngins.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.entity.postgresql.PageActionPermission;
import com.ngins.spring.jpa.entity.postgresql.PageActionPermissionId;
/**
 * 
 */
@Repository
public interface PageActionPermissionRepositiry extends JpaRepository<PageActionPermission, PageActionPermissionId> {

}
