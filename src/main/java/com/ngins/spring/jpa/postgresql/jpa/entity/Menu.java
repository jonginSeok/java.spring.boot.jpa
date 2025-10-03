/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "menu_key", length = 100, nullable = false)
	private String menu_key;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 255)
	private String path;

	@Column
	private long parent_id;

	@Column(nullable = false, columnDefinition = "Integer default 0")
	private Integer display_order;

	// columnDefinition 속성은 PostgreSQL에서만 동작
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean is_enabled;

}
