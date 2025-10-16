package com.ngins.spring.jpa.postgresql.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.UserRoleGroup;

@Repository
public interface UserRoleGroupRepository extends JpaRepository<UserRoleGroup, Long> {

    Optional<UserRoleGroup> findByUsername(String username);
}
