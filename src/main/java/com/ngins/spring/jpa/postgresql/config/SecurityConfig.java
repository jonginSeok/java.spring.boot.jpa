package com.ngins.spring.jpa.postgresql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
// @PreAuthorize 사용
public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
//						.requestMatchers(HttpMethod.GET, "/menus/**").authenticated().anyRequest().authenticated())
//				.oauth2ResourceServer(oauth -> oauth.jwt(null)); // 또는 커스텀 JWT 필터
//
//		return http.build();
//	}

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

	/*
	 * @Bean Converter<Jwt, AbstractAuthenticationToken>
	 * jwtAuthenticationConverter(UserAuthorityService authorityService) {
	 * 
	 * return new Converter<Jwt, AbstractAuthenticationToken>() {
	 * 
	 * @Override public AbstractAuthenticationToken convert(Jwt token) { String
	 * username = token.getClaimAsString("sub"); // Collection<GrantedAuthority>
	 * authorities = // authorityService.loadAuthorities(username);
	 * Collection<SimpleGrantedAuthority> authorities =
	 * authorityService.loadAuthorities(username); return new
	 * JwtAuthenticationToken(token, authorities); } }; }
	 */

	/**
	 * 패스워드 암호화 관련 메소드
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	/**
	 * 특정 HTTP 요청에 대한 웹 기반 보안 구성
	 * 시큐리티 대부분의 설정을 담당하는 메소드
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
					(authorize) -> authorize
					.requestMatchers("/signup", "/", "/login").permitAll()
					.anyRequest().authenticated()
			)
			// Form 로그인을 활용하는경우 (JWT에는 필요없음)
			.formLogin(
					form -> form
					.loginPage("/loginform")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/").permitAll()
			)
			.logout(
					(logout) -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
			)
			.sessionManagement(
					sessionManagement -> sessionManagement
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					
				// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);

		return http.build();

	} // 출처: https://eesko.tistory.com/333 [미정:티스토리]

}
