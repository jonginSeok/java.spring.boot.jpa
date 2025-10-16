package com.ngins.spring.jpa.postgresql.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ngins.spring.jpa.postgresql.jpa.entity.PageAction;
import com.ngins.spring.jpa.postgresql.jpa.entity.Permission;
import com.ngins.spring.jpa.postgresql.jpa.entity.Role;
import com.ngins.spring.jpa.postgresql.jpa.repository.PageActionRepository;
import com.ngins.spring.jpa.postgresql.jpa.repository.PermissionRepository;

/**
 * 메서드 보안과 커스텀 PermissionEvaluator </br>
 * 버튼 단위 제어를 위해 화면(page_key)와 action_key를 검사하는 서비스 메서드를 제공합니다.
 */
@Component("authz")
public class PageAuthorizationService {

	private final PageActionRepository pageActionRepo;

	@SuppressWarnings("unused")
	private final PermissionRepository permissionRepo;

	public PageAuthorizationService(PageActionRepository pageActionRepo, PermissionRepository permissionRepo) {
		this.pageActionRepo = pageActionRepo;
		this.permissionRepo = permissionRepo;
	}

	public boolean canPerform(Authentication auth, String pageKey, String actionKey) {
		if (isAdmin(auth))
			return true;

		// page_key + action_key 에 매핑된 Permission 또는 Role 을 보유하는지 검사
		List<PageAction> actions = pageActionRepo.findByPageKeyAndActionKey(pageKey, actionKey);
		if (actions.isEmpty())
			return false;

		for (var pa : actions) { // pageAction
			// Permission 검사
			var perms = pa.getPermissions().stream().map(this::permAuthorityKey).toList();
			for (var a : auth.getAuthorities()) {
				if (perms.contains(a.getAuthority()))
					return true;
			}
			// Role 검사
			var roles = pa.getRoles().stream().map(Role::getRoleKey).toList();
			// Java 10부터 도입된 지역 변수 타입 추론 기능
			for (var a : auth.getAuthorities()) {
				if (roles.contains(a.getAuthority()))
					return true;
			}
		}
		return false;
	}

	private boolean isAdmin(Authentication auth) {
		return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
	}

	private String permAuthorityKey(Permission p) {
		return "PERM_" + p.getResourceKey() + ":" + p.getActionKey();
	}

}
