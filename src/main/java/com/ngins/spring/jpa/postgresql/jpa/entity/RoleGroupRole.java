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
@Table(name = "tbl_role_group_role", comment = "역할그룹역할")
public class RoleGroupRole {

}
