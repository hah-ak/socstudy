package com.example.socstudy.main.blizzard.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BlizzardApiService {

    public String getBlizzardUserInfo(HttpServletRequest request) {
        try {
            String attribute = request.getSession().getAttribute("BLIZZARD_TOKEN").toString();
            return new BlizzardDataApi().getOauthUseAPi(attribute,"https://kr.battle.net/oauth/userinfo");
        } catch (Exception e) {

        }
        return "";
    }
}
