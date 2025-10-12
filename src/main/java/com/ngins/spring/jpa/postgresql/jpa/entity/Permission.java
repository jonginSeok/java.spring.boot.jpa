/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

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
 * 엔티티 및 JPA 매핑 예시
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "resource_key", length = 100, nullable = false)
	private long resourceKey;

	@Column(name = "action_key", length = 100, nullable = false)
	private String actionKey; // VIEW, QUERY, SAVE, EXPORT_XLS, IMPORT_XLS

	@Column(name = "description", length = 255)
	private String description;

}
