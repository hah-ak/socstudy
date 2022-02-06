package com.example.socstudy.util;


import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CookieService {

    public static <T> Cookie createCookie(T a) {
        String cookieValue = "";
        if (a instanceof AuthorizationTokenVo) {
            AuthorizationTokenVo vo = (AuthorizationTokenVo) a;
//            cookieValue = "ACCESS_TOKEN=" + vo.getAccessToken();
            cookieValue = vo.getAccessToken();

        }

        Cookie cookie = new Cookie("ACCESS_TOKEN",cookieValue);
        cookie.setDomain("");
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        return cookie;
    }

    public static Cookie sessionIdCookie(HttpServletRequest request) {
        Cookie cookie = new Cookie("SESSIONID",request.getSession().getId());
        cookie.setDomain("");
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        return cookie;
    }
}
