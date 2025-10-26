/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class UserRoleGroupId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298886339901579268L;

	private AppUser user_id;

	private RoleGroup role_group_id;
}
