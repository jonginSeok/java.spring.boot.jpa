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
//@Entity
@Embeddable
@Table(name = "menu_required_role")
public class MenuRequiredRole {

	@Column(nullable = false)
	private long menu_id;

	@Column(nullable = false)
	private long role_id;
	
}
