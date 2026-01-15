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
@Table(name = "tbl_user_role_group", comment = "사용자권한그룹")
@IdClass(UserRoleGroupId.class)
public class UserRoleGroup {

	@Id
	@Column(name = "user_id", nullable = false, comment = "사용자ID")
	private Long userId;

	@Id
	@Column(name = "role_group_id", nullable = false, comment = "역할그룹ID")
	private Long roleGroupId;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_user_role_group_fkey1"))
	private AppUser appUser;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "role_group_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_user_role_group_fkey2"))
	private RoleGroup roleGroup;

}
