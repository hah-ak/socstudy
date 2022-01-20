package com.example.socstudy.oAuth2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationCodeHandler {

    private BufferedWriter bufferedWriter = null;
    private BufferedReader bufferedReader = null;
    HttpURLConnection connection = null;
    private final String CLIENT_ID = "7a62452e6f294562bb1519c087c299fd";
    private final String CLIENT_SECRET = "tyWTEtCvEF9fRR3SyyA5OET6AEirKmtJ";
    private final String GRANT_TYPE = "authorization_code";
    private final String AUTHORIZE_REQUEST_URI = "https://kr.battle.net/oauth/authorize";
    private final String ACCESS_TOKEN_REQUEST_URI = "https://kr.battle.net/oauth/token";
    public String getAuthorizationRequestURL() {

        StringBuilder fullURL = new StringBuilder();
        fullURL.append(AUTHORIZE_REQUEST_URI);
        fullURL.append("?client_id="+CLIENT_ID);
        fullURL.append("&scope=sc2.profile");
        fullURL.append("&redirect_uri=http://localhost:3003");
        fullURL.append("&response_type=code");

        return fullURL.toString();

    }

    public String getAccessToken(String code) throws IOException {

        StringBuilder returnValue = new StringBuilder();

        try {
            String encodedCredentials = Base64.getEncoder().encodeToString(String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes(StandardCharsets.UTF_8));
            StringBuilder fullUrl = new StringBuilder();
            fullUrl.append(ACCESS_TOKEN_REQUEST_URI);
            fullUrl.append("?grant_type=" + GRANT_TYPE);
            fullUrl.append("&code=" + code);
            fullUrl.append("&redirect_uri=http://localhost:3003");
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
        } catch (Exception e) {
            System.out.println(e);
            returnValue.append("error");
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return returnValue.toString();
    }

}
