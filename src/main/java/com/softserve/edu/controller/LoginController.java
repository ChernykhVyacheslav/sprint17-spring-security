package com.softserve.edu.controller;

import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login-form")
    public String login() {
        return "login-page";
    }

    @RequestMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
