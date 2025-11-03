package com.ngins.spring.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 패스워드 암호화 관련 메소드
	@Bean // DelegatingPasswordEncoder: 여러 인코딩 알고리즘을 사용할 수 있게 해주는 기능
	static PasswordEncoder passwordEncoder() {

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	} // 출처: https://eesko.tistory.com/333 [미정:티스토리]

	/**
	 * 특정 HTTP 요청에 대한 웹 기반 보안 구성 <br>
	 * 시큐리티 대부분의 설정을 담당하는 메소드
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable) // 메소드 참조
				.httpBasic(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize // 람다식
						.requestMatchers("/signup", "/", "/login").permitAll())
				
				// Form 로그인을 활용하는경우 (JWT에는 필요없음)
				.formLogin(form -> form
						.loginPage("/loginform")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/")
						.permitAll())
				
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				
				.sessionManagement(sessionManagement -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.maximumSessions(1)
						.maxSessionsPreventsLogin(true))
				
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.anyRequest().permitAll() // 마지막에 한 번만 사용해야 함

				// Spring Security에서 HttpSecurity를 설정할 때 authorizeRequests() 또는
				// authorizeHttpRequests()를 사용하여 요청 경로에 대한 권한을 설정합니다.
				// 이때 anyRequest()는 모든 요청에 대한 마지막 규칙으로 사용해야 합니다.
				);

		return http.build();

	} // 출처: https://eesko.tistory.com/333 [미정:티스토리]

	// 이외에도 등록해서 사용하면 된다..

}
