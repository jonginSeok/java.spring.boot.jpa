package com.ngins.spring.jpa.postgresql.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ngins.spring.jpa.postgresql.jpa.entity.AppUser;
import com.ngins.spring.jpa.postgresql.jpa.entity.Permission;
import com.ngins.spring.jpa.postgresql.jpa.repository.UserRepository;

/**
 * 권한 로딩 서비스 예시:
 */
@Service
public class UserAuthorityService {
    
    private final UserRepository userRepo;

    public UserAuthorityService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserAuthorityService() {
        this.userRepo = null;
        //TODO Auto-generated constructor stub
    }

    public Collection<GrantedAuthority> loadAuthorities(String username) {
        AppUser u = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다: " + username));
        
        Set<String> auths = new HashSet<>();

        // 관리자면 모든 권한 부여.
        if (u.isAdmin()) {
            auths.add("ROLE_ADMIN");
            auths.add("PERM_ALL");
            return auths.stream().map(SimpleGrantedAuthority::new).toList();
        }

        // 직접 Role
        u.getRoles().forEach(r -> auths.add(r.getRoleKey()));

        // RoleGroup → Role
        u.getRoleGroups().forEach(g -> g.getRoles().forEach(r -> auths.add(r.getRoleKey())));

        // Role → Permission
        u.getRoles().forEach(r -> r.getPermissions().forEach(p -> auths.add(permissionKey(p))));
        u.getRoleGroups()
                .forEach(g -> g.getRoles().forEach(r -> r.getPermissions().forEach(p -> auths.add(permissionKey(p)))));

        return auths.stream().map(SimpleGrantedAuthority::new).toList();
    }

    private String permissionKey(Permission p) {
        // "PERM_inventory:SAVE" 형태로 부여
        return "PERM_" + p.getResourceKey() + ":" + p.getActionKey();
    }

}
