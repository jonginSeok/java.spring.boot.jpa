package com.ngins.spring.jpa.entity.postgresql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 엔티티 및 JPA 매핑
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_role_permission", comment = "역할허용")
@IdClass(RolePermissionId.class)
public class RolePermission {

	@Id
	@Column(name = "role_id", nullable = false, comment = "역할ID")
	private Long roleId;
	
	@Id
	@Column(name = "permission_id", nullable = false, comment = "허용ID")
	private Long permissionId;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_role_permission_fkey1")) // , nullable = false
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_role_permission_fkey2")) // , nullable = false
	private Permission permission;

}
