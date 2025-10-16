package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngins.spring.jpa.postgresql.jpa.entity.AuditLog;

public interface AuditLogRepositiry extends JpaRepository<AuditLog, Long> {

}
