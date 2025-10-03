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
@Table(name = "menu_required_permission")
public class MenuRequiredPermission {

	@Column(nullable = false)
	private long menu_id;

	@Column(nullable = false)
	private long permission_id;
	
}
