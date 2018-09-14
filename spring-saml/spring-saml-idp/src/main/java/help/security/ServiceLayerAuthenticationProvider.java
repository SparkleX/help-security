package help.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayerAuthenticationProvider implements AuthenticationProvider,AuthenticationManager
{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{

		String name = authentication.getName();
		ServiceLayerAuthenticationDetails details = (ServiceLayerAuthenticationDetails)authentication.getDetails();
		String password = authentication.getCredentials().toString();
		if (password.equals("Initial0") == false)
		{
			throw new BadCredentialsException("");
		}
	//	String company = details.getCompany();
		String sessionId = "SESSION-ID-FROM-SERVICE_LAYER";
		return new ServiceLayerAuthenticationToken(authentication.getPrincipal(), password, sessionId, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}