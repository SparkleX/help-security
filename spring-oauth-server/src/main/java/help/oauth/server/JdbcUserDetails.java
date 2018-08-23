package help.oauth.server;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JdbcUserDetails implements UserDetails
{
	private static final long serialVersionUID = 1530096698767795797L;
	private String name;

	public JdbcUserDetails(String name) 
	{
		this.name = name;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return null;
	}

	public String getPassword() {
		return "1234";
	}

	public String getUsername() {
		return name;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() 
	{
		return true;
	}

}
