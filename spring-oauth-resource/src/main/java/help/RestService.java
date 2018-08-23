package help;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService
{
	@RequestMapping("/test")
	public String test(Principal principal)
	{
		return "hello " + principal.getName();
	}
}
