/**
 * 
 */
package com.ngins.spring.jpa.entity.postgresql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_menu_required_permission", comment = "메뉴필수허용")
@IdClass(MenuRequiredPermissionId.class)
public class MenuRequiredPermission {

	@Id
	@Column(name = "menu_id", nullable = false, comment = "메뉴ID")
	private Long menuId;

	@Id
	@Column(name = "permission_id", nullable = false, comment = "허용ID")
	private Long permissionId;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_menu_required_permission_fkey1"))
	private Menu menu;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_menu_required_permission_fkey2"))
	private Permission permission;

}
