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
@Table(name = "tbl_menu", comment = "메뉴")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "menu_key", length = 100, nullable = false)
	private String menuKey;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "path", length = 255)
	private String path;
	
	
	
	// 부모 메뉴 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_menu_fkey1"), comment = "메뉴부모ID")
	private Menu parent;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(name = "display_order", nullable = false, columnDefinition = "int default 0")
	private Integer displayOrder;

	@Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true")
	private Boolean isEnabled;
	
	
	
	// 자식 메뉴 설정
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) // 외래키 설정
	private List<Menu> children = new ArrayList<>();

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuRequiredRole> menuRequiredRoles = new ArrayList<>();

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuRequiredPermission> menuRequiredPermissions = new ArrayList<>();

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Page> pages = new ArrayList<>();

}
