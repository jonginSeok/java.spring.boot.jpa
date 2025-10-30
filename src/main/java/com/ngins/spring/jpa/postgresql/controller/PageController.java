package com.ngins.spring.jpa.postgresql.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 허용 액션 조회 API 예시(화면 진입 시 호출):
 */
@RestController
@RequestMapping("/pages")
public class PageController {
    
    @GetMapping("/{pageKey}/actions")
    public Set<String> allowedActions(Authentication auth, @PathVariable String pageKey) {
        
        // 해당 pageKey의 모든 action_key 중 @authz.canPerform(...)가 true인 것만 반환
        /**
         *  예: {"QUERY","SAVE","EXPORT_XLS"}
         */
        
        return Collections.emptySet(); // Ex
    }

}
