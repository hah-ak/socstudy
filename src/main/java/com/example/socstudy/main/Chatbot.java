package com.example.socstudy.main;

import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.chat.v1.HangoutsChat;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;

public class Chatbot {
    private String scope = "https://www.googleapis.com/auth/chat.bot";

    private final String PUBLIC_CERT_URL_PREFIX = "https://www.googleapis.com/service_accounts/v1/metadata/x509/";
    private final String AUDIENCE = "";

    public void credentials() {
        try {
            JsonFactory jsonFactory = new GsonFactory();

//            GoogleCredentials credentials = GoogleCredentials.fromStream(Chatbot.class.getResourceAsStream("/service-acct.json")).createScoped(scope);
//            HttpRequestInitializer initializer = new HttpCredentialsAdapter(credentials);
//            HangoutsChat hangoutsChat = new HangoutsChat.Builder(
//                    GoogleNetHttpTransport.newTrustedTransport(), jsonFactory,
//            )

            GooglePublicKeysManager.Builder keyMangerBuiler = new GooglePublicKeysManager.Builder(new ApacheHttpTransport(), jsonFactory);

        } catch (Exception e) {

        }

    }

}
