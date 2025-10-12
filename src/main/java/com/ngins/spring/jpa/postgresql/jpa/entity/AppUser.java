/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
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
@Table(name = "app_user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(name = "username", length = 100, nullable = false)
	private String username;

	@Column(name = "email", length = 255)
	private String email;

	@Column(name = "password_hash", length = 255, nullable = false)
	private String passwordHash;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;

	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_role_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_group_id"))
	private Set<RoleGroup> roleGroups = new HashSet<>();

	@Builder
	public AppUser(String username, String passwordHash, Set<Role> roles, Set<RoleGroup> roleGroups) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.roles = roles;
		this.roleGroups = roleGroups;
	}

}
