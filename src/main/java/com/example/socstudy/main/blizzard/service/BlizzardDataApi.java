package com.example.socstudy.main.blizzard.service;


import com.example.socstudy.oAuth2.ClientCredentialHandler;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
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

    public String getOauthUseAPi(HttpServletRequest request, String mUrl) throws IOException {
        String remoteAddr = request.getRemoteAddr();
        String sessionAddr = (String) request.getSession().getAttribute("IP");

        if ( !sessionAddr.equals(remoteAddr) && remoteAddr != null && sessionAddr != null) {
            throw new IOException("ip 불량");
        }

        String token = request.getSession().getAttribute("BLIZZARD_TOKEN").toString();
        URL url = new URL(mUrl);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Authorization","Bearer " + token);
        httpURLConnection.setRequestProperty("content-Type",MediaType.APPLICATION_JSON_VALUE);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

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
