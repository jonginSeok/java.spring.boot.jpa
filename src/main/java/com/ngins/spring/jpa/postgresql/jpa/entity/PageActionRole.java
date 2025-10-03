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
//@Entity
@Embeddable
@Table(name = "page_action_role")
public class PageActionRole {

	@Column(nullable = false)
	private long page_action_id;

	@Column(nullable = false)
	private long role_id;
	
}
