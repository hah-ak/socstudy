package com.example.socstudy.main.blizzard.controller;


import com.example.socstudy.main.blizzard.service.BlizzardApiService;
import com.example.socstudy.main.blizzard.service.AuthorizationService;
import com.example.socstudy.main.blizzard.vo.Sc2ProfileReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
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


    @GetMapping("/sc2Player")
    public String getUserInfo(@RequestParam int profileId, HttpServletRequest request) {
        String get = blizzardApiService.getSc2Player(request,profileId);
        return get;
    }

    @PostMapping("/sc2Profile")
    public String getData(@RequestBody Sc2ProfileReqVo sc2ProfileReqVo, HttpServletRequest request) {
        String get = blizzardApiService.getSc2Profile(request, sc2ProfileReqVo);
        return get;
    }

    @GetMapping("/blizzardLogin")
    public String getAuthURL(HttpServletRequest request) {
        return authorizationService.getAuthUrl(request);
    }

    @GetMapping(value = "/setToken")
    public String getAccessCode(@RequestParam(value = "code") String code,
                                HttpServletRequest request, HttpServletResponse response) {
        return authorizationService.setToken(code, request, response);
    }

    @PostMapping("/owPlayer/insert")
    public void insert() {

    }
}
