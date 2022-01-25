package com.example.socstudy.oAuth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class OauthKey {

    @Value("classpath:/static/json/service-acct.json")
    private Resource resource;
    public static final HashMap<ApiKeyEnum,String> BLIZZARD_API_KEY_MAP = new HashMap<>();
    public static final HashMap<ApiKeyEnum,String> GOOGLE_API_KEY_MAP = new HashMap<>();

    @Bean(initMethod = "initAPIMAP")
    public setAPIClass getResource() {
        return new setAPIClass();
    }

    public class setAPIClass {
        public void initAPIMAP() {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<String,HashMap<String,String>> map = objectMapper.readValue(resource.getURL(), new HashMap<String,HashMap<String,String>>().getClass());
                map.forEach((key,value)->{
                    if (key.equals("BLIZZARD_API_KEY_MAP")) {
                        Arrays.stream(ApiKeyEnum.values()).forEach(e->{
                            BLIZZARD_API_KEY_MAP.put(e,value.get(e.keyName));
                        });
                    } else {
                        Arrays.stream(ApiKeyEnum.values()).forEach(e->{
                            GOOGLE_API_KEY_MAP.put(e,value.get(e.keyName));
                        });
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    enum ApiKeyEnum {
        CLIENT_ID("CLIENT_ID"),CLIENT_SECRET("CLIENT_SECRET"),API_KEY("API_KEY");
        ApiKeyEnum(String keyName) {
            this.keyName = keyName;
        }
        private String keyName;
    }
}
