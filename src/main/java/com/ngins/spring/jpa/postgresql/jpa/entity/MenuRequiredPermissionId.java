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
public class MenuRequiredPermissionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1775146367317250806L;

	private Long menu_id;

	private Long permission_id;

}
