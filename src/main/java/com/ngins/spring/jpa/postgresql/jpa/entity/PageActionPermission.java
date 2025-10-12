/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
// @Entity
@Embeddable
@Table(name = "page_action_permission")
public class PageActionPermission {

	@Column(name = "page_action_id", nullable = false)
	private long pageActionId;

	@Column(name = "permission_id", nullable = false)
	private long permissionId;

}
