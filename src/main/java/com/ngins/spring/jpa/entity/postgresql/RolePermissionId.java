package com.ngins.spring.jpa.entity.postgresql;

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
public class RolePermissionId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3989679794290040196L;

	private Long roleId;

	private Long permissionId;
}
