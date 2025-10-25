/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_menu_require_role", comment = "메뉴필수역할")
public class MenuRequiredRole {

}
