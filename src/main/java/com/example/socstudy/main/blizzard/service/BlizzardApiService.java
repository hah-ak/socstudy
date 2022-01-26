package com.example.socstudy.main.blizzard.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BlizzardApiService {

    public String getBlizzardUserInfo(HttpServletRequest request) {
        try {
            return new BlizzardDataApi().getOauthUseAPi(request.getHeader("BLIZZARD"),"https://kr.battle.net/oauth/userinfo");
        } catch (Exception e) {

        }
        return "";
    }
}
