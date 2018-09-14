package help.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails
{
	private static final long serialVersionUID = 1530096698767795797L;
	private String name;
	private String server;
	private String company;

	
	public MyUserDetails(String server, String company, String name) 
	{
		this.server = server;
		this.company = company;
		this.name = name;
	}
	public MyUserDetails(String name) 
	{
		this.name = name;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return null;
	}
	public String getServer() {
		return server;
	}
	public String getCompany() {
		return company;
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
