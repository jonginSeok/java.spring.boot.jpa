package com.ngins.spring.jpa.postgresql.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngins.spring.jpa.postgresql.jpa.entity.Menu;

/**
 * 메뉴 API 예시(프런트가 네비게이션 구성에 사용):
 */
@RestController
@RequestMapping("/menus")
public class MenuController {
    
    @GetMapping
    public List<Menu> myMenus(Authentication auth) {
        
        // 현재 사용자 권한으로 접근 가능한 메뉴만 반환
        // 메뉴에 요구되는 Role/Permission과 교집합이 있는지 검사해서 필터링
        return null;
    }

}
