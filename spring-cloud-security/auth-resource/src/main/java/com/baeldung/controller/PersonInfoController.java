package com.baeldung.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoController {

    @GetMapping("/person")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public @ResponseBody String personInfo() 
    {
        return "personInfo : 1";
    }   
}
