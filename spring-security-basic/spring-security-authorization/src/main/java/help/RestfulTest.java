package help;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestfulTest 
{
    @RequestMapping("/test1")
    public String test1() 
    {
        return "test1";
    }
    
    @RequestMapping("/test2")
    public String test2() 
    {
        return "test2";
    }

}
