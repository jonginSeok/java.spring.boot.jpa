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
 * 엔티티 및 JPA 매핑
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_page_action_role", comment = "페이지액션역할")
@IdClass(PageActionRoleId.class)
public class PageActionRole {

	@Id
	@Column(name = "page_action_id", nullable = false, comment = "페이지액션ID")
	private Long pageActionId;

	@Id
	@Column(name = "role_id", nullable = false, comment = "역할ID")
	private Long roleId;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "page_action_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_page_action_role_fkey1"))
	private PageAction pageAction;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_page_action_role_fkey2"))
	private Role role;

}
