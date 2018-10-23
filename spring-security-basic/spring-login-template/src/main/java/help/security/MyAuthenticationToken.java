package help.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class MyAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = -7239204299515554429L;

	/*public String getSession() {
		return session;
	}

	private String session;*/
	
	private Object credentials;

	public MyAuthenticationToken(MyUserDetails userDetails, String credentials)
	{
		super(new ArrayList<>());
		this.setDetails(userDetails);
		this.credentials = credentials;
		//this.session = session;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() 
	{
		return this.getDetails();
	}

}
