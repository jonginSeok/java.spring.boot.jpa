package com.ngins.spring.jpa.postgresql.config;

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

//	@Bean // 패스워드 암호화 관련 메소드
//	public PasswordEncoder passwordEncoder() {
//
//		// return new BCryptPasswordEncoder();
//		// return new StandardPasswordEncoder(); // The constructor StandardPasswordEncoder() is deprecated
//		// return new NoOpPasswordEncoder().getInstance(); // The constructor NoOpPasswordEncoder() is not visible
//		// The static method getInstance() from the type NoOpPasswordEncoder should be accessed in a static way
//		// int cpuCost, int memoryCost, int parallelization, int keyLength, int saltLength
//		return new SCryptPasswordEncoder(0, 0, 0, 0, 0);
//	}

	@Bean // DelegatingPasswordEncoder: 여러 인코딩 알고리즘을 사용할 수 있게 해주는 기능
	public static PasswordEncoder passwordEncoder() {
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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.csrf(AbstractHttpConfigurer::disable) // 메소드 참조
				.httpBasic(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorize) -> authorize // 람다식
						.requestMatchers("/signup", "/", "/login").permitAll()
						//.anyRequest().authenticated() // anyRequest()는 모든 요청에 대한 마지막 규칙으로 사용해야 합니다.
				)
				// Form 로그인을 활용하는경우 (JWT에는 필요없음)
				.formLogin(form -> form
						.loginPage("/loginform")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/")
						.permitAll()
				)
				.logout((logout) -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
				)
				.sessionManagement((sessionManagement) -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.maximumSessions(1)
						.maxSessionsPreventsLogin(true)
				)				
				.authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.anyRequest().permitAll()  // 마지막에 한 번만 사용해야 함
                        
                // Spring Security에서 HttpSecurity를 설정할 때 authorizeRequests() 또는 authorizeHttpRequests()를 사용하여 요청 경로에 대한 권한을 설정합니다. 
                // 이때 anyRequest()는 모든 요청에 대한 마지막 규칙으로 사용해야 합니다.

                );

		return http.build();

	} // 출처: https://eesko.tistory.com/333 [미정:티스토리]

	// 이외에도 등록해서 사용하면 된다..

//	@Bean // oauth2
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
//						.requestMatchers(HttpMethod.GET, "/menus/**").authenticated().anyRequest().authenticated())
//				.oauth2ResourceServer(oauth -> oauth.jwt(null)); // 또는 커스텀 JWT 필터
//
//		return http.build();
//	}

//	@Bean // jwt
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

	
//	@Bean 
//	Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter(UserAuthorityService authorityService) {
//		return new Converter<Jwt, AbstractAuthenticationToken>() {
//			@Override
//			public AbstractAuthenticationToken convert(Jwt token) {
//				String username = token.getClaimAsString("sub");
//				// Collection<GrantedAuthority> authorities = // authorityService.loadAuthorities(username);
//				Collection<SimpleGrantedAuthority> authorities = authorityService.loadAuthorities(username);
//				return new JwtAuthenticationToken(token, authorities);
//			} 
//		}; 
//	}
	 

}
