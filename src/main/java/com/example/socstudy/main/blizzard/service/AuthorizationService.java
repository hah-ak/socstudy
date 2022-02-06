package com.example.socstudy.main.blizzard.service;


import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import com.example.socstudy.util.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Service
public class AuthorizationService {

    public String setToken(String code, HttpServletRequest request ,HttpServletResponse response) {

        AuthorizationTokenVo vo = new AuthorizationTokenVo();;
        try {
            vo = new AuthorizationCodeHandler().getAccessToken(code);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }

        response.addCookie(CookieService.createCookie(vo));
        request.getSession().setAttribute("BLIZZARD_TOKEN",vo.getAccessToken());
        return vo.getAccessToken();

    }

    public String getAuthUrl(HttpServletRequest request) {
        try {
            String result = new AuthorizationCodeHandler().getAuthorizationRequestURL(request);
            return result;
        } catch (Exception e) {
            return "error";
        }

    }
}
