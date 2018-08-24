package help.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
public class PersonInfoController 
{
    @GetMapping("/person")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public @ResponseBody String person() 
    {
        return "personInfo : 2";
    }
    
    @GetMapping("/callOtherServiceByTemplate")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public @ResponseBody String callOtherServiceViaTemplate() 
    {
    	RestOperations restOperations = new RestTemplate();
    	SecurityContext secCtx = SecurityContextHolder.getContext();
    	Authentication auth = secCtx.getAuthentication();
    	OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails) auth.getDetails();
    	String token = detail.getTokenValue();
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", "Bearer "+token);
    	HttpEntity<String> entity = new HttpEntity<String>(headers);
        String personResourceUrl = "http://localhost:9001/person";
        String data = restOperations.exchange(personResourceUrl,HttpMethod.GET, entity, String.class).getBody();
        
        return "resource 2 call resource 1 return : " + data;
    } 
    
    
	@Autowired
	Service1 service1;

    
    @GetMapping("/callOtherServiceByFeign")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public @ResponseBody String callOtherServiceByFeign() 
    {
    	/*SecurityContext secCtx = SecurityContextHolder.getContext();
    	Authentication auth = secCtx.getAuthentication();
    	OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails) auth.getDetails();
    	String token = detail.getTokenValue();
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", "Bearer "+token);*/

    	String data = service1.test();//"Bearer "+token);
        return "resource 2 call resource 1 via FEIGN return : " + data;
    } 
}
