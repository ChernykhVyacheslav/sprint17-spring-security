package com.softserve.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login-form")
    public String login() {
        return "login-page";
    }

    @GetMapping("/login")
    public String loginAttempt() {
        return "login-page";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }

}
