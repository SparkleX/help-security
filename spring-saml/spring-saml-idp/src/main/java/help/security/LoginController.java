package help.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController 
{
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() 
	{
		return "login";
	}
	@RequestMapping("/logout")
	public String logout() 
	{
		return "logout";
	}
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute LoginForm loginForm,
            BindingResult result) 
	{
		MyUserDetails userDetails = ((MyUserDetailsService)userDetailsService).loadUserByUsername(loginForm.getServer(), loginForm.getCompany(), loginForm.getUsername());
		
		ServiceLayerAuthenticationToken usernamePasswordAuthenticationToken 
		= new ServiceLayerAuthenticationToken(userDetails, loginForm.getPassword(),null,null);
		Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(auth); 
		return "redirect:/test2";
	}
	

}