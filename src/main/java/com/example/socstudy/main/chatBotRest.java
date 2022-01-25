package com.example.socstudy.main;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class chatBotRest {

    @GetMapping("/goChat")
    public void goChat() {
//        String protocol = "https://";
//        String authurl = "accounts.google.com/o/oauth2/v2/auth";
//        String scope = "?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&";
//        String accessType = "access_type=offline&";
//        String grantScop = "include_granted_scopes=true&";
//        String rsponseType = "response_type=code&";
//        String redirectUri= "redirect_uri=http%3A//localhost:8085/chat/goChat&";
//        String cliendId = "client_id=" + "149990219719-js4kinu4mj83rvamjcsibeqlne53pkob.apps.googleusercontent.com";
//        try {
//            URL url = new URL(protocol+authurl + scope + accessType + grantScop + rsponseType + redirectUri + cliendId);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.getResponseCode();
//            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
//            int read = 0;
//            while((read = inputStream.read()) > 0) {
//
//            }
//        } catch (Exception e) {
//
//        }



    }
}
