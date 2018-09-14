package help.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ServiceLayerAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = -7239204299515554429L;

	public String getSession() {
		return session;
	}

	private String session;

	public MyUserDetails getUserDetails() {
		return userDetails;
	}

	MyUserDetails userDetails;

	public ServiceLayerAuthenticationToken(Object principal, Object credentials,String session,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.userDetails = (MyUserDetails) principal;
		this.session = session;
	}

}
