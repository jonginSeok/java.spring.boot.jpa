/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "user_role_group")
public class UserRoleGroup {

	@Column(nullable = false)
	private long user_id;

	@Column(nullable = false)
	private long role_group_id;
	
}
