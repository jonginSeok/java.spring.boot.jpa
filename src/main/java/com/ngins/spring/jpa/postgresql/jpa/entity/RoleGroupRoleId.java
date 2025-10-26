/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 복합 키 클래스
 */
@Getter
@Setter
@EqualsAndHashCode
public class RoleGroupRoleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3062093515259606564L;

	private Long role_group_id;

	private Long role_id;

}
