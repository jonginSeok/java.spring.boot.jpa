/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tbl_menu", comment = "메뉴")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "메뉴ID")
	private long id;

	@Column(name = "menu_key", length = 100, nullable = false, comment = "메뉴키")
	private String menuKey;

	@Column(name = "name", length = 100, nullable = false, comment = "메뉴이름")
	private String name;

	@Column(name = "path", length = 255, comment = "메뉴경로")
	private String path;

	@Column(name = "parent_id", nullable = false, comment = "메뉴부모ID")
	private Long parentId;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(name = "display_order", nullable = false, columnDefinition = "int default 0", comment = "표시순서")
	private Integer displayOrder;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true", comment = "활성여부")
	private Boolean isEnabled;

	@OneToMany(mappedBy = "menu_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuRequiredRole> menuRequiredRoles = new ArrayList<MenuRequiredRole>();

}
