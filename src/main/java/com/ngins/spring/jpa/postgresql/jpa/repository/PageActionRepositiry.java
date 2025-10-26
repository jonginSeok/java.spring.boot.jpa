/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.Page;
/**
 * 
 */
@Repository
public interface PageActionRepositiry extends JpaRepository<Page, Long> {

}
