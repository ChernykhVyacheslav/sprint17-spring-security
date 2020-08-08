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


    @PostMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @RequestMapping(value="/csrf-token", method=RequestMethod.GET)
    public @ResponseBody
    String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }
}
