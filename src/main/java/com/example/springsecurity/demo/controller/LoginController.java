package com.example.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/showMyLoginPage") // matches with the demoSecurityConfig route
  public String showMyloginPage() {

    return "fancy-login";
  }


  @GetMapping("/access-denied") // matches with the demoSecurityConfig route
  public String showAccessDenied() {

    return "access-denied";
  }
}
