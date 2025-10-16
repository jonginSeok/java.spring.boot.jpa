package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngins.spring.jpa.postgresql.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
