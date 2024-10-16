package com.miladsadeghi.financial.management.controller;

import com.miladsadeghi.financial.management.model.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/admin")
public class AdminController {


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/dashboard",method = RequestMethod.GET)
    public ResponseEntity adminDashboard(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String message = null;
        if (principal instanceof CustomUserDetails) {
            message = ((CustomUserDetails) principal).getUsername();
        } else {
            message = "success login";
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
