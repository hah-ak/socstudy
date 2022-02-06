package com.example.socstudy.oAuth2;

import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class AuthorizationCodeHandler {

    private BufferedWriter bufferedWriter = null;
    private BufferedReader bufferedReader = null;
    HttpURLConnection connection = null;
    private final String CLIENT_ID = OauthKey.BLIZZARD_API_KEY_MAP.get(OauthKey.ApiKeyEnum.CLIENT_ID);
    private final String CLIENT_SECRET = OauthKey.BLIZZARD_API_KEY_MAP.get(OauthKey.ApiKeyEnum.CLIENT_SECRET);
    private final String GRANT_TYPE = "authorization_code";
    private final String AUTHORIZE_REQUEST_URI = "https://kr.battle.net/oauth/authorize";
    private final String ACCESS_TOKEN_REQUEST_URI = "https://kr.battle.net/oauth/token";


    public String getAuthorizationRequestURL(HttpServletRequest request) throws Exception {

        String sessionId = request.getSession().getId();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(sessionId.getBytes(StandardCharsets.UTF_8));
        String str = new String(digest.digest(),StandardCharsets.UTF_8);
        String urlStr = URLEncoder.encode(str,StandardCharsets.UTF_8);

        StringBuilder fullURL = new StringBuilder();
        fullURL.append(AUTHORIZE_REQUEST_URI);
        fullURL.append("?client_id="+CLIENT_ID);
        fullURL.append("&scope=sc2.profile");
        fullURL.append("&redirect_uri=http://localhost:3003/getCode");
        fullURL.append("&state=" + urlStr);
        fullURL.append("&response_type=code");

        request.getSession().setAttribute("state",digest);

        return fullURL.toString();

    }

    public synchronized AuthorizationTokenVo getAccessToken(String code) throws IOException {
    //state 검증이 필요하다.
        StringBuilder returnValue = new StringBuilder();

        String encodedCredentials = Base64.getEncoder().encodeToString(String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes(StandardCharsets.UTF_8));
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(ACCESS_TOKEN_REQUEST_URI);
        fullUrl.append("?grant_type=" + GRANT_TYPE);
        fullUrl.append("&code=" + code);
        fullUrl.append("&redirect_uri=http://localhost:3003/getCode");
        fullUrl.append("&client_id=" + CLIENT_ID);
        fullUrl.append("&client_secret="+CLIENT_SECRET);

        URL url = new URL(fullUrl.toString());
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Authorization",String.format("Basic %s",encodedCredentials));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bufferedWriter.flush();
        bufferedWriter.close();

        String line;

        if (connection.getResponseCode() != 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }

        while ((line=bufferedReader.readLine())!= null) {
            returnValue.append(line);
        }

        bufferedReader.close();
        connection.disconnect();

        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (bufferedWriter != null) {
            bufferedWriter.close();
        }
        if (connection != null) {
            connection.disconnect();
        }

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        AuthorizationTokenVo vo = gson.fromJson(returnValue.toString(), AuthorizationTokenVo.class);
        return vo;
    }

}
