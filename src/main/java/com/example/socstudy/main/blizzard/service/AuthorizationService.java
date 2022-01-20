package com.example.socstudy.main.blizzard.service;


import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthorizationService {

    public void getToken(String code, HttpServletResponse response) {
        try {
            AuthorizationTokenVo vo = new Gson().fromJson(new AuthorizationCodeHandler().getAccessToken(code), AuthorizationTokenVo.class);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
