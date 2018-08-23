package help.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import help.security.database.Users;

public class UserDetailsImpl implements UserDetails 
{
	private static final long serialVersionUID = -6492123246712799281L;
	private Users user;
 
    public UserDetailsImpl(Users user) 
    {
        this.user = user;
    }
 	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return null;
	}

	public String getPassword()
	{
		return this.user.getPassword();
	}

	public String getUsername()
	{
		return this.user.getUsername();
	}

	public boolean isAccountNonExpired()
	{
		return true;
	}

	public boolean isAccountNonLocked()
	{
		return true;
	}

	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	public boolean isEnabled()
	{
		return true;
	}
}