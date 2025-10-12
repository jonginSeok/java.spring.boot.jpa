package com.ngins.spring.jpa.postgresql.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngins.spring.jpa.postgresql.jpa.entity.AppUser;

public interface PageActionRepository extends JpaRepository<AppUser, Long> {

    Object findByPageKeyAndActionKey(String pageKey, String actionKey);

}
