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
 * 엔티티 및 JPA 매핑 예시
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(PageActionPermissionId.class)
@Table(name = "tbl_page_action_permission", comment = "페이지액션허용")
public class PageActionPermission {

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_action_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_role_permission_fkey1"), comment = "페이지액션ID")
	private PageAction page_action_id;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_role_permission_fkey2"), comment = "허용ID")
	private Permission permission_id;

}
