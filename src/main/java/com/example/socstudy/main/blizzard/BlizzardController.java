package com.example.socstudy.main.blizzard;


import com.example.socstudy.main.blizzard.service.AuthorizationService;
import com.example.socstudy.oAuth2.AuthorizationCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blizzard")
public class BlizzardController {

    private final AuthorizationService authorizationService;
    @Autowired
    public BlizzardController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
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
    public String getUserInfo() {
        String get;
        try {
            get = new BlizzardDataApi().getSc2UserInfo();
        } catch (Exception e) {
            get="error";
        }
        return get;
    }

    @GetMapping("/getAuthURL")
    public String getAuthURL() {
        return new AuthorizationCodeHandler().getAuthorizationRequestURL();
    }

    @GetMapping(value = "/getCode")
    public void getAccessCode(@RequestParam(value = "code") String code, HttpServletResponse response) {
        authorizationService.getToken(code, response);
    }
}
