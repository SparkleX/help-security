package help.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService 
{
	public MyUserDetailsService()
	{
		
	}
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException 
	{
		return new MyUserDetails(name);
	}
	public MyUserDetails loadUserByUsername(String server, String company, String name) throws UsernameNotFoundException 
	{
		return new MyUserDetails(server, company, name);
	}
}
