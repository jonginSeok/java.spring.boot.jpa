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
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_page_action", comment = "페이지액션")
public class PageAction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, comment = "페이지액션ID")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_page_action_fkey1"), comment = "페이지ID")
	private Page page_id;
	
	
	@Column(name = "action_key", length = 120, nullable = false, comment = "액션KEY")
	private String actionKey;

	@Column(name = "description", length = 255, comment = "상세")
	private String description;
	
	

	

	@OneToMany(mappedBy = "page_action_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PageActionRole> pageActionRoles = new ArrayList<PageActionRole>();

	@OneToMany(mappedBy = "page_action_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PageActionPermission> pageActionPermissions = new ArrayList<PageActionPermission>();

}
