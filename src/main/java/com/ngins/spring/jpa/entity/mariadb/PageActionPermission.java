package com.ngins.spring.jpa.entity.mariadb;

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
@Table(name = "tbl_page_action_permission", comment = "페이지액션허용")
@IdClass(PageActionPermissionId.class)
public class PageActionPermission {

	@Id
	@Column(name = "page_action_id", nullable = false, comment = "페이지액션ID")
	private Long pageActionId;

	@Id
	@Column(name = "permission_id", nullable = false, comment = "허용ID")
	private Long permissionId;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "page_action_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_page_action_permission_fkey1"))
	private PageAction pageAction;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_page_action_permission_fkey2"))
	private Permission permission;

}
