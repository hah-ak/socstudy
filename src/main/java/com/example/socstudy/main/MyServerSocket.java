package com.example.socstudy.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class MyServerSocket {

    public static ServerSocket serverSocket;
    private Socket socket;

    public MyServerSocket() {
        makeSocket();
        socket = null;
    }

    public void makeSocket() {
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(8086);
            }

        } catch (Exception e) {
            System.out.println("serverSocket => " + e);
        }
    }
    public void acceptSocket(String text) {
        try {
            socket = serverSocket.accept();

//            InputStream inputStream = socket.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            String message = "";
//            while((line = bufferedReader.readLine()) != null) {
//                message += line;
//            }
            String message = text;
            OutputStream outputStream = socket.getOutputStream();
            message = "socketTest" + message;
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
//            inputStream.close();
            outputStream.close();
            System.out.println("serverSocket send => " + message);
            socket.close();
        } catch (Exception e) {
            System.out.println("serverSocket => " + e);
        } finally {
            if (!serverSocket.isClosed()) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                }
            }
        }
    }
}
