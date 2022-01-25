package com.example.socstudy.oAuth2;

import com.google.gson.Gson;
import org.apache.catalina.webresources.war.Handler;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLStreamHandler;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class ClientCredentialHandler {

    private final Object tokenLock = new Object();
    private String token = null;
    private Instant tokenExpiry = null;

    private final String CLIENT_ID = OauthKey.BLIZZARD_API_KEY_MAP.get(OauthKey.ApiKeyEnum.CLIENT_ID);
    private final String CLIENT_SECRET = OauthKey.BLIZZARD_API_KEY_MAP.get(OauthKey.ApiKeyEnum.CLIENT_SECRET);

    private final String TOKEN_URI = "https://kr.battle.net/oauth/token";

    private BufferedReader bufferedReader = null;
    HttpURLConnection connection = null;

    public String getToken() throws IOException {
        if (isTokenInvalid()) {
            String encodedCredentials = Base64.getEncoder().encodeToString(String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes(StandardCharsets.UTF_8));


            URLStreamHandler urlStreamHandler = new Handler();
            try {
                URL url = new URL(TOKEN_URI);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization",String.format("Basic %s",encodedCredentials));
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.getOutputStream().write("grant_type=client_credentials".getBytes(StandardCharsets.UTF_8));

                int resCode = connection.getResponseCode();
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                if (resCode == 200) {
                    while ((line= bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                }
                TokenResponse g = new Gson().fromJson(sb.toString(),TokenResponse.class);

                synchronized (tokenLock) {
                    tokenExpiry = Instant.now().plusSeconds(g.getExpires_in());
                    token = g.getAccess_token();
                }
            } catch (Exception e) {

            } finally {
                bufferedReader.close();
                connection.disconnect();
            }
        }
        synchronized (tokenLock) {
            return token;
        }
    }

    public boolean isTokenInvalid() {
        synchronized (tokenLock) {
            if (token == null) {
                return true;
            }
            if (tokenExpiry == null) {
                return true;
            }

            return Instant.now().isAfter(tokenExpiry);
        }
    }
}
