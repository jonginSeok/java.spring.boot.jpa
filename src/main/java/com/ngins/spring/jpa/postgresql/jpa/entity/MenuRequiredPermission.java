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
@Table(name = "menu_required_permission")
public class MenuRequiredPermission {

	@Column(name = "menu_id", nullable = false)
	private long menuId;

	@Column(name = "permission_id", nullable = false)
	private long permissionId;

}
