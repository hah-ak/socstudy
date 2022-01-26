package com.example.socstudy.main.blizzard.service;


import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import com.example.socstudy.util.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthorizationService {

    public String setToken(String code, HttpServletResponse response) {

        AuthorizationTokenVo vo = new AuthorizationTokenVo();;
        try {
            vo = new AuthorizationCodeHandler().getAccessToken(code);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
        response.addCookie(CookieService.createCookie(vo));
        return vo.getAccessToken();

    }

    public String getAuthUrl() {
        return new AuthorizationCodeHandler().getAuthorizationRequestURL();
    }
}
