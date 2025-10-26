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
public class MenuRequiredRoleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7512445508333619716L;

	private Long menu_id;

	private Long role_id;
}
