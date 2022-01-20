package com.example.socstudy.main;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Main {

    @RequestMapping("/")
    public String main(HttpServletRequest httpServletRequest, Model model) {
        try {

        } catch (Exception e) {

        }


        return "index";
    }

    @PostMapping("/soc")
    @ResponseBody
    public String socTest(@RequestBody String text) {
        MyServerSocket myServerSocket = new MyServerSocket();
        ClientSocket clientSocket = new ClientSocket();
        myServerSocket.makeSocket();
        clientSocket.makeClientSocket();
        myServerSocket.acceptSocket(text);
        clientSocket.clientStream();
        return text;
    }


}
