package com.miladsadeghi.financial.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/user")
public class UserController {

    @RequestMapping(path = "/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "/dashboard";
    }

}
