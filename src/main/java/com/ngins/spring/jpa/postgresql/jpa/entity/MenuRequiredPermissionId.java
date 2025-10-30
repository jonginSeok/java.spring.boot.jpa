package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 복합 키 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MenuRequiredPermissionId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1775146367317250806L;

	private Long menuId;
	
	private Long permissionId;

}
