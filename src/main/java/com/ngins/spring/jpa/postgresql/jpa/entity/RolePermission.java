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
@Table(name = "role_permission")
public class RolePermission {

	@Column(nullable = false)
	private long role_id;

	@Column(nullable = false)
	private long permission_id;
	
}
