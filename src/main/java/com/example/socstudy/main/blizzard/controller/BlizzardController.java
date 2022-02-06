package com.example.socstudy.main.blizzard.controller;


import com.example.socstudy.main.blizzard.service.BlizzardApiService;
import com.example.socstudy.main.blizzard.service.BlizzardDataApi;
import com.example.socstudy.main.blizzard.service.AuthorizationService;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/blizzard")
public class BlizzardController {

    private final AuthorizationService authorizationService;
    private final BlizzardApiService blizzardApiService;
    @Autowired
    public BlizzardController(AuthorizationService authorizationService, BlizzardApiService blizzardApiService) {
        this.authorizationService = authorizationService;
        this.blizzardApiService = blizzardApiService;
    }

    @GetMapping("/blizzardUserInfo")
    public String getBlizzardUserInfo(HttpServletRequest request) {
        return blizzardApiService.getBlizzardUserInfo(request);
    }
    @GetMapping("/sc3")
    public String getData() {
        String get;
        try {
            get = new BlizzardDataApi().getSC2Data();
        } catch (Exception e) {
            get = "error";
        }
        return get;
    }

    @GetMapping("/sc2UserInfo")
    public String getUserInfo(HttpServletRequest request) {

        String get;
        try {
//            get = new BlizzardDataApi().getSc2UserInfo();
            get = "";
        } catch (Exception e) {
            get="error";
        }
        return get;
    }

    @GetMapping("/blizzardLogin")
    public String getAuthURL(HttpServletRequest request) {
        System.out.println(request);
        return authorizationService.getAuthUrl(request);
    }

    @GetMapping(value = "/setToken")
    public String getAccessCode(@RequestParam(value = "code") String code,
                                HttpServletRequest request, HttpServletResponse response) {
        return authorizationService.setToken(code, request, response);
    }
}
