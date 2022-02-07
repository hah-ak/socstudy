package com.example.socstudy.main.blizzard.vo;

import lombok.Getter;

@Getter
public class Sc2ProfileReqVo {
    private String region = "kr";
    private String avatarUrl;
    private String name;
    private int regionId;
    private int realmId;
    private String profileId;
    private String locale = "ko_KR";
}
