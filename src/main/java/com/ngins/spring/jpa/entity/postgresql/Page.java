package com.ngins.spring.jpa.entity.postgresql;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * 엔티티 및 JPA 매핑
 */
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "tbl_page", comment = "페이지")
public class Page {

	public Page(Long id, String pageKey, String name) {
		super();
		this.id = id;
		this.pageKey = pageKey;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "페이지ID")
	private Long id;

	@Column(name = "page_key", length = 120, nullable = false, comment = "페이지KEY")
	private String pageKey;

	@Column(name = "name", length = 120, nullable = false, comment = "이름")
	private String name;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_page_fkey1"))
	private Menu menu;

	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true) // 외래키 설정
	private List<PageAction> pageActions = new ArrayList<>();

}
