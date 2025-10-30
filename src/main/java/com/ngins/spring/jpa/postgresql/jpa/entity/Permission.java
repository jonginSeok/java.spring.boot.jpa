/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

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
@Table(name = "tbl_permission", comment = "허용")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, comment = "허용ID")
	private Long id;

	@Column(name = "resource_key", length = 100, nullable = false, comment = "리소스키")
	private String resourceKey;

	@Column(name = "action_key", length = 100, nullable = false, comment = "액션키")
	private String actionKey; // VIEW, QUERY, SAVE, EXPORT_XLS, IMPORT_XLS

	@Column(name = "description", length = 255, comment = "상세")
	private String description;

	
	
	
	@OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PageActionPermission> pageActionPermissions = new ArrayList<>(); // Replace the type specification in this constructor call with the diamond operator ("<>").

	@OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuRequiredPermission> menuRequiredPermissions = new ArrayList<>();

	@OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RolePermission> rolePermissions = new ArrayList<>();

}
