/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_menu_required_permission", comment = "메뉴필수허용")
public class MenuRequiredPermission {

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_menu_required_permission_fkey1"), comment = "메뉴ID")
	private Menu menu_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_menu_required_permission_fkey2"), comment = "메뉴ID")
	private Permission permission_id;

}
