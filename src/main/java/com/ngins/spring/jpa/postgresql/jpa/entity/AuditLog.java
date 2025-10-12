/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "event", length = 200, nullable = false)
	private String event;

	@Column(name = "details", columnDefinition = "jsonb")
	private String details;

	@CreatedDate
	@Column(name = "occurred_at", nullable = false)
	private LocalDateTime occurredAt;

}
