package com.example.socstudy.main.blizzard.service;


import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import com.example.socstudy.util.CookieService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthorizationService {

    public void setToken(String code, HttpServletResponse response) {

        AuthorizationTokenVo vo = new AuthorizationTokenVo();;
        try {
            vo = new AuthorizationCodeHandler().getAccessToken(code);
            vo.wait();
            System.out.println(vo);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }
        System.out.println(vo.getAccessToken());
        response.addCookie(CookieService.createCookie(vo));
    }
}
