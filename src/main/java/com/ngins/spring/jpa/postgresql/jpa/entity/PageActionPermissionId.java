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
public class PageActionPermissionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7284522448274099236L;

	private Long page_action_id;

	private Long permission_id;
}
