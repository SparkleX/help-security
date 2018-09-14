package help;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService 
{
	@Autowired
	HttpServletRequest request;
	
	public void test()
	{
		Principal p = request.getUserPrincipal();
		System.out.println(p.getName());
	}
}
