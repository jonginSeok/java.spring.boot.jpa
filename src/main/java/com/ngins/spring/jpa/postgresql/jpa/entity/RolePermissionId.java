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
public class RolePermissionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3989679794290040196L;

	private Long role_id;
	
	private Long permission_id;
}
