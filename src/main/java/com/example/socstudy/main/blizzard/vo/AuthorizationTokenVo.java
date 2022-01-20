package com.example.socstudy.main.blizzard.vo;


import lombok.Getter;

@Getter
public class AuthorizationTokenVo {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String scope;
    private String sub;
}
