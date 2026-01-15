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
@Table(name = "tbl_user_role", comment = "사용자역할")
@IdClass(UserRoleId.class)
public class UserRole {

	@Id
	@Column(name = "user_id", nullable = false, comment = "사용자ID")
	private Long userId;

	@Id
	@Column(name = "role_id", nullable = false, comment = "역할ID")
	private Long roleId;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_user_role_fkey1"))
	private AppUser appUser;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "tbl_user_role_fkey2"))
	private Role role;

}
