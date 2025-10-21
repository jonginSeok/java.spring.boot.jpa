/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_menu", comment = "메뉴")
public class Menu {

//	// 생성자
// 	@NoArgsConstructor 와 대응된다.
//	protected Menu() {
//	}

//	public Menu(String name, Menu parent) {
//		this.name = name;
//		this.parent = parent;
//	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "메뉴ID")
	private Long id;

	@Column(name = "menu_key", length = 100, nullable = false, comment = "메뉴키")
	private String menuKey;

	@Column(name = "name", length = 100, nullable = false, comment = "메뉴이름")
	private String name;

	@Column(name = "path", length = 255, comment = "메뉴경로")
	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "tbl_menu_fkey1"), comment = "메뉴 부모ID")
	private Menu parent;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(name = "display_order", nullable = false, columnDefinition = "int default 0", comment = "표시순서")
	private Integer displayOrder;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true", comment = "활성여부")
	private Boolean isEnabled;

	@OneToMany(mappedBy = "menu_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuRequiredRole> menuRequiredRoles = new ArrayList<MenuRequiredRole>();

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Menu> children = new ArrayList<Menu>();

}
