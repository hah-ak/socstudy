package com.example.socstudy.main.blizzard.service;

import com.example.socstudy.main.blizzard.vo.Sc2ProfileReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class BlizzardApiService {

    public String getBlizzardUserInfo(HttpServletRequest request) {
        try {
            String result = new BlizzardDataApi().getOauthUseAPi(request, "https://kr.battle.net/oauth/userinfo");
            return result;
        } catch (Exception e) {
            log.error("error" + e);
            System.out.println(e);
            return "";
        }
    }

    public String getSc2Player(HttpServletRequest request,int profileId) {
        try {
            String url = String.format("https://%s.api.blizzard.com/sc2/player/%d","kr",profileId);
            String result = new BlizzardDataApi().getOauthUseAPi(request,url);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    public String getSc2Profile(HttpServletRequest request, Sc2ProfileReqVo vo) {
        try {
            String url = String.format("https://kr.api.blizzard.com/sc2/profile/" + vo.getRegionId() + "/" + vo.getRealmId() + "/" +vo.getProfileId());
            String result = new BlizzardDataApi().getOauthUseAPi(request,url);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
}
