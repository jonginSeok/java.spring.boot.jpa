/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 엔티티 및 JPA 매핑 예시
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "tbl_user_role_group", comment = "사용자권한그룹")
public class UserRoleGroup {

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_user_role_group_fkey1"), comment = "사용자ID")
	private AppUser user_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_group_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_user_role_group_fkey2"), comment = "역할그룹ID")
	private RoleGroup role_group_id;

}
