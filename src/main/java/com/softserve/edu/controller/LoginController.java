package com.softserve.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    @Autowired
//    AuthenticationManager authManager;

    @GetMapping("/login-form")
    public String login() {
        return "login-page";
    }

//    @PostMapping("/login")
//    public String loginAttempt(HttpServletRequest req, String user, String pass) {
//        UsernamePasswordAuthenticationToken authReq
//                = new UsernamePasswordAuthenticationToken(user, pass);
//        Authentication auth = authManager.authenticate(authReq);
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
//        HttpSession session = req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
//        return "home";
//    }

//    @GetMapping("/access-denied")
//    public String showAccessDenied() {
//
//        return "access-denied";
//
//    }

}
