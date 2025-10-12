/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
// @Entity
@Embeddable
@Table(name = "role_group_role")
public class RoleGroupRole {

	@Column(name = "role_group_id", nullable = false)
	private long roleGroupId;

	@Column(name = "role_id", nullable = false)
	private long roleId;

}
