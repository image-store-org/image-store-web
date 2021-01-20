package com.vartdalen.imagestoreweb.configuration.authentication;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String credentials;

	public ApiAuthenticationToken(String token) {
		super(null);
		this.credentials = token;
		setAuthenticated(false);
	}

	public Object getPrincipal() {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) {
		super.setAuthenticated(isAuthenticated);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}
}
