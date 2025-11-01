package com.ngins.spring.jpa.entity.mariadb;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 엔티티 및 JPA 매핑
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_role_group", comment = "역할그룹")
public class RoleGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "역할그룹ID")
	private Long id;

	@Column(name = "group_key", length = 100, nullable = false, comment = "그룹키")
	private String groupKey;

	@Column(name = "name", length = 100, nullable = false, comment = "이름")
	private String name;

	@Column(name = "description", length = 255, comment = "설명")
	private String description;

	
	
	
	@OneToMany(mappedBy = "roleGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserRoleGroup> userRoleGroups = new ArrayList<>();

	@OneToMany(mappedBy = "roleGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleGroupRole> roleGroupRoles = new ArrayList<>();

}
