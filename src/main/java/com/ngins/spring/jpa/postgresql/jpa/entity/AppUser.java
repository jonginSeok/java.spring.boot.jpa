/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "app_user")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(name = "username", length = 100, nullable = false)
	private String username;

	@Column(length = 255)
	private String email;

	@Column(length = 255, nullable = false)
	private String password_hash;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_admin;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime created_at;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updated_at;

}
