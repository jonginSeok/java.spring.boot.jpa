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
public class PageActionRoleId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8858263503503969394L;

	private Long pageActionId;

	private Long roleId;

}
