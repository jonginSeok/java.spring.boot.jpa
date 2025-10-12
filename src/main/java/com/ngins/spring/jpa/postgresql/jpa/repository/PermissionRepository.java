package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngins.spring.jpa.postgresql.jpa.entity.AppUser;

public interface PermissionRepository extends JpaRepository<AppUser, Long> {

}
