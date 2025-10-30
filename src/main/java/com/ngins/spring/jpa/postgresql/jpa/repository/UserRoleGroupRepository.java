/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngins.spring.jpa.postgresql.jpa.entity.UserRoleGroup;
import com.ngins.spring.jpa.postgresql.jpa.entity.UserRoleGroupId;

/**
 * 
 */
@Repository
public interface UserRoleGroupRepository extends JpaRepository<UserRoleGroup, UserRoleGroupId> {

    // 특정 사용자에 대한 모든 RoleGroup 조회
    List<UserRoleGroup> findByUserId(Long userId);

    // 특정 RoleGroup에 속한 모든 사용자 조회
    List<UserRoleGroup> findByRoleGroupId(Long roleGroupId);

    // 사용자 ID와 RoleGroup ID로 특정 관계 조회
    UserRoleGroup findByUserIdAndRoleGroupId(Long userId, Long roleGroupId);

    // 관계 삭제
    void deleteByUserIdAndRoleGroupId(Long userId, Long roleGroupId);
}
