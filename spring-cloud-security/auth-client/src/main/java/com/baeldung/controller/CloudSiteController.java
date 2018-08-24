package com.baeldung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CloudSiteController {

    @Autowired
    private RestOperations restOperations;

    @GetMapping("/")
    @ResponseBody
    public String helloFromBaeldung() {
        return "Hello From Baeldung!";
    }

    @GetMapping("/person1")
    public String person1() 
    {
        String personResourceUrl = "http://localhost:9001/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    
    @GetMapping("/person2")
    public String person2() 
    {
        String personResourceUrl = "http://localhost:9002/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
    
    @GetMapping("/person3")
    public String person3() 
    {
        String personResourceUrl = "http://localhost:9002/person3";
        return restOperations.getForObject(personResourceUrl, String.class);
    }
}