package com.ngins.test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.ngins.spring.jpa.postgresql.service.UserAuthorityService;

public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }
	
	@Bean
	public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter(UserAuthorityService authorityService) {
	    return token -> {
	        String username = token.getClaimAsString("sub");
	        // Collection<GrantedAuthority> authorities = authorityService.loadAuthorities(username);
	        Collection<SimpleGrantedAuthority> authorities = authorityService.loadAuthorities(username);
	        return new JwtAuthenticationToken(token, authorities);
	    };
	}

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("custom_roles");
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

	
}
