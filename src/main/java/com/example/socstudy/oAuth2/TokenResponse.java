package com.example.socstudy.oAuth2;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private String token_type;
    private Long expires_in;
}
