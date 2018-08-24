package help.oauth.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class GatewayController {

    @Autowired
    private RestOperations restOperations;

    @GetMapping("/")
    @ResponseBody
    public String helloFromBaeldung() 
    {
        return "Hello From Baeldung!";
    }

    @GetMapping("/test1")
    public String test1() 
    {
        String personResourceUrl = "http://localhost:9001/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    
    @GetMapping("/test2")
    public String test2() 
    {
        String personResourceUrl = "http://localhost:9002/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    
    @GetMapping("/test3")
    public String test3() 
    {
        String personResourceUrl = "http://localhost:9002/callOtherServiceByTemplate";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    @GetMapping("/test4")
    public String test4() 
    {
        String personResourceUrl = "http://localhost:9002/callOtherServiceByFeign";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    
}