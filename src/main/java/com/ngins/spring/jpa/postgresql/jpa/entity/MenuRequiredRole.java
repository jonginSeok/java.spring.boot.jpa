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
 * 엔티티 및 JPA 매핑 예시
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_menu_required_role", comment = "메뉴필수역할")
public class MenuRequiredRole {
	
	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_menu_require_role_fkey1"), comment = "메뉴ID")
	private Menu menu_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_menu_require_role_fkey2"), comment = "역할ID")
	private Role role_id;
}
