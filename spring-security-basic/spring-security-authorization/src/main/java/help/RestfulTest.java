package help;

import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @PreAuthorize("hasAnyRole('ADMIN', 'XXXX')")
    @RequestMapping("/test2")
    public String test2() 
    {
        return "test2";
    }

}
