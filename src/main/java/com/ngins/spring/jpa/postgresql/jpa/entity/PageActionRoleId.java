/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 복합 키 클래스
 */
@Getter
@Setter
public class PageActionRoleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8858263503503969394L;
	
	private Long page_action_id;
	
	private Long role_id;

}
