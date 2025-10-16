package com.ngins.spring.jpa.postgresql.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngins.spring.jpa.postgresql.jpa.entity.PageAction;

public interface PageActionRepository extends JpaRepository<PageAction, Long> {

    // permissions[permission, authority], authorities[authority]
    List<PageAction> findByPageKeyAndActionKey(String pageKey, String actionKey);

}
