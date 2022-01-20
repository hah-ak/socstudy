package com.example.socstudy.main.blizzard;


import com.example.socstudy.oAuth2.ClientCredentialHandler;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BlizzardDataApi {

    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;

    public String getSC2Data() throws IOException {
        String token = new ClientCredentialHandler().getToken();

        URL url = new URL("https://kr.api.blizzard.com/data/sc2/league/37/201/0/6?locale=ko_KR&access_token="+token);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-Type",MediaType.APPLICATION_JSON_VALUE);
        httpURLConnection.setDoInput(true);

        int responseCode = httpURLConnection.getResponseCode();
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        if (responseCode == 200) {
            while((line=bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        httpURLConnection.disconnect();
        bufferedReader.close();

        return sb.toString();
    }

    public String getSc2UserInfo() throws IOException {
        String token = new ClientCredentialHandler().getToken();

        URL url = new URL("https://kr.battle.net/oauth/userinfo?access_token=" + token);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
//        httpURLConnection.setRequestProperty("Authorization","Bearer " + token);
        httpURLConnection.setRequestProperty("content-Type",MediaType.APPLICATION_JSON_VALUE);
        httpURLConnection.setDoInput(true);

        int responseCode = httpURLConnection.getResponseCode();
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        if (responseCode == 200) {
            while((line=bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        httpURLConnection.disconnect();
        bufferedReader.close();

        return sb.toString();
    }

}
