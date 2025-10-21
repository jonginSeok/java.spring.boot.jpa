/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Column;
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
 * 
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "tbl_role_permission", comment = "역할허용")
public class RolePermission {

	@Column(name = "role_id", nullable = false)
	private long roleId;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_role_permission_fkey1"), comment = "허용ID")
	private Permission permission;
	
}
