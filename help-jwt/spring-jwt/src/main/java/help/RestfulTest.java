package help;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestfulTest 
{
    @RequestMapping("/test1")
    public String test1() 
    {
    	String userName = null;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) 
    	{
    		userName = authentication.getName();
    		for(GrantedAuthority a:authentication.getAuthorities())
    		{
    			System.out.println(a.getAuthority());
    		}
    	}
        return String.format("user:%s", userName);
    }
    
    
    @Autowired
    MyService myService;
    
    @RequestMapping("/test2")
    public String test2(Principal principal) 
    {
    	myService.test();
        return principal.getName();
    }
    @RequestMapping("/hello")
    public String hello(){

        return "hello";
    }

   /* @RequestMapping("/userList")
    public Map<String, Object> userList(){
        List<User> myUsers = applicationUserRepository.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("users",myUsers);
        return map;
    }*/

    @RequestMapping("/admin")
    public String admin(){

        return "admin";
    }
}
