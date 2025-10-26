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
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_page", comment = "페이지")
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "페이지ID")
	private Long id;

	@Column(name = "page_key", length = 120, nullable = false, comment = "페이지KEY")
	private String pageKey;

	@Column(name = "name", length = 120, nullable = false, comment = "이름")
	private String name;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", comment = "메뉴ID")
	private Menu menu_id;

	@OneToMany(mappedBy = "page_id", cascade = CascadeType.ALL, orphanRemoval = true) // 외래키 설정
	private List<PageAction> pageActions = new ArrayList<PageAction>();

}
