package com.ngins.spring.jpa.postgresql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

import com.ngins.spring.jpa.postgresql.jwt.JwtAuthenticationConverter;
import com.ngins.spring.jpa.postgresql.service.UserAuthorityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Security 설정 (JWT + 권한 매핑)
 */
@Configuration
@RequiredArgsConstructor
// @EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .disable()
            ) // CSRF 비활성화 (API 서버이므로)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/menus/**").authenticated()
                
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/user/**").hasAuthority("ROLE_USER")

                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(jwtAuthenticationConverter(new UserAuthorityService()))    
                    //.jwkSetUri("https://auth-server.com/.well-known/jwks.json")
                )
            ); // 또는 커스텀 JWT 필터
        return http.build();
    }

    /**
     * JWT 토큰에서 권한을 추출하는 커스텀 컨버터(커스텀 인증 객체 사용)
     * 
     * 토큰 예시
     * 예를 들어, 다음과 같은 JWT가 있다고 가정하면:
     * {
        "sub": "jongin",
        "scope": ["user", "admin"]
        }

     * @param authorityService
     * @return
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(UserAuthorityService authorityService) {
        // return token -> {
        //     // 토큰에서 username 추출 후 DB에서 Role/Permission 집합 로드
        //     String username = token.getClaimAsString("sub");
        //     Collection<GrantedAuthority> authorities = authorityService.loadAuthorities(username);
        //     var auth = new JwtAuthenticationToken(token, authorities);
        //     return auth;
        // };

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("ROLE_"); // "scope" -> "ROLE_scope"
        authoritiesConverter.setAuthoritiesClaimName("scope"); // 또는 "scp", "roles" 등

        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;

    }

}
