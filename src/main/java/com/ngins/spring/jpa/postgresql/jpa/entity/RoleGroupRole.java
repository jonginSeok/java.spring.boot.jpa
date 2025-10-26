/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(RoleGroupRoleId.class)
@Table(name = "tbl_role_group_role", comment = "역할그룹역할")
public class RoleGroupRole {

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_group_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_role_group_role_fkey1"), comment = "역할그룹ID")
	private RoleGroup role_group_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_role_group_role_fkey2"), comment = "역할ID")
	private Role role_id;
}
