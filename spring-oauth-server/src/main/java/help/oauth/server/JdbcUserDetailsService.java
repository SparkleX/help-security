package help.oauth.server;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JdbcUserDetailsService implements UserDetailsService 
{
	JdbcUserDetailsService()
	{
		
	}
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException 
	{
		return new JdbcUserDetails(name);
	}

}
