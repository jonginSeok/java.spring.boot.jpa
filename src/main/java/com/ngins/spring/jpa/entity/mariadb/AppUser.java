package com.ngins.spring.jpa.entity.mariadb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tbl_app_user", comment = "앱사용자")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "앱사용자ID")
	private Long id;

	@Column(name = "username", length = 100, nullable = false, comment = "사용자이름")
	private String username;

	@Column(name = "email", length = 255, comment = "이메일")
	private String email;

	@Column(name = "password_hash", length = 255, nullable = false, comment = "암호해쉬")
	private String passwordHash;

	@Column(name = "is_active", nullable = false, comment = "활성여부")
	private Boolean isActive;

	@Column(name = "is_admin", nullable = false, comment = "관리자여부")
	private Boolean isAdmin;

	@CreatedDate
	@Column(name = "created_at", nullable = false, comment = "생성일시")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false, comment = "수정일시")
	private LocalDateTime updatedAt;
	
	
	
	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AuditLog> auditLogs = new ArrayList<>();
	
	
	
	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRole> userRoles = new ArrayList<>();

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRoleGroup> userRoleGroups = new ArrayList<>();

}
