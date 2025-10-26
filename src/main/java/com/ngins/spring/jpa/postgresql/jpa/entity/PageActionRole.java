/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.IdClass;
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
@IdClass(PageActionRoleId.class)
@Table(name = "tbl_page_action_role", comment = "페이지액션역할")
public class PageActionRole {

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_action_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_page_action_role_fkey1"), comment = "페이지액션ID")
	private PageAction page_action_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_page_action_role_fkey2"), comment = "역할ID")
	private Role role_id;

}
