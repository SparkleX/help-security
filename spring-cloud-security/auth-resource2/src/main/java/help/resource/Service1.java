package help.resource;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="foo",url="http://localhost:9001")
public interface Service1 
{
    @RequestMapping(method = RequestMethod.GET, value = "/person")
    String test(/*@RequestHeader("Authorization") String auth*/);
}