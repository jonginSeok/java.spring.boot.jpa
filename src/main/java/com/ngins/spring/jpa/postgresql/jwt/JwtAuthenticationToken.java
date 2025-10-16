package com.ngins.spring.jpa.postgresql.jwt;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -197497280837386631L;

	public JwtAuthenticationToken(Jwt token, @Nullable Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		// this.setAuthenticated(true);
	}

	@Override
	public @Nullable Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
