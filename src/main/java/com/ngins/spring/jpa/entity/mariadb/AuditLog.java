package com.ngins.spring.jpa.entity.mariadb;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_audit_log", comment = "심사로그")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "심사로그ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "tbl_audit_log_fkey1"), comment = "사용자ID")
	private AppUser appUser;

	@Column(name = "event", length = 200, nullable = false, comment = "이벤트")
	private String event;

	// @Column(name = "details", columnDefinition = "JSONB", comment = "상세") // MariaDB 에서 오류./JSONB
	@Column(name = "details", columnDefinition = "LONGTEXT", comment = "상세")
	private String details;

	@CreatedDate
	@Column(name = "occurred_at", nullable = false, comment = "발생일시")
	private LocalDateTime occurredAt;

}
