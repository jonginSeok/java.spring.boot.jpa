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
public class UserRoleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4148148640137309213L;

	private Long user_id;
	
	private Long role_id;
}
