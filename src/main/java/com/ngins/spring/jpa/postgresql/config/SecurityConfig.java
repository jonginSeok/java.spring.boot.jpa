package com.ngins.spring.jpa.postgresql.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import com.ngins.spring.jpa.postgresql.jwt.JwtAuthenticationToken;
import com.ngins.spring.jpa.postgresql.service.UserAuthorityService;


@Configuration
@EnableWebSecurity // @PreAuthorize 사용
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/menus/**").authenticated().anyRequest().authenticated())
				.oauth2ResourceServer(oauth -> oauth.jwt(null)); // 또는 커스텀 JWT 필터

		return http.build();
	}

//	@Bean
//	public JwtAuthenticationConverter jwtAuthenticationConverter(UserAuthorityService authorityService) {
//
//		return token -> {
//			// 토큰에서 username 추출 후 DB에서 Role/Permission 집합 로드
//			String username = token.getClaimAsString("sub");
//			Collection<GrantedAuthority> authorities = authorityService.loadAuthorities(username);
//
//			return authorities;
//		};
//	}
	
	@Bean
	public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter(UserAuthorityService authorityService) {
	    return new Converter<Jwt, AbstractAuthenticationToken>() {
	        @Override
	        public AbstractAuthenticationToken convert(Jwt token) {
	            String username = token.getClaimAsString("sub");
	            //Collection<GrantedAuthority> authorities = authorityService.loadAuthorities(username);
	            Collection<SimpleGrantedAuthority> authorities = authorityService.loadAuthorities(username);
	            return new JwtAuthenticationToken(token, authorities);
	        }
	    };
	}

}
