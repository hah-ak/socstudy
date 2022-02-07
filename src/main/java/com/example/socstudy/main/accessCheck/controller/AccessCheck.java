package com.example.socstudy.main.accessCheck.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/accessCheck")
public class AccessCheck {

    @GetMapping("/check")
    public boolean check(HttpServletRequest request) {
        boolean isFirst = request.getSession().isNew();
        String ip = request.getHeader("x-forwarded-for");
        if (isFirst) {
            if (ip == null  || "unknown".equalsIgnoreCase(ip)) {
                request.getSession().setAttribute("IP",request.getRemoteAddr());
            } else {
                request.getSession().setAttribute("IP",ip);
            }
        }
        return isFirst;
    }
}
