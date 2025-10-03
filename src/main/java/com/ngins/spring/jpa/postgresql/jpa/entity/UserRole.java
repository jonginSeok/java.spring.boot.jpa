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
@Table(name = "user_role")
public class UserRole {

	@Column(nullable = false)
	private long user_id;

	@Column(nullable = false)
	private long role_id;
	
}
