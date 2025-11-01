package com.ngins.spring.jpa.entity.mariadb;

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
public class PageActionPermissionId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7284522448274099236L;

	private Long pageActionId;

	private Long permissionId;
}
