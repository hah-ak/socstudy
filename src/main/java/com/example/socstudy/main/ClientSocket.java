package com.example.socstudy.main;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocket {
    private Socket socket;

    public void makeClientSocket() {
        try {
            socket = new Socket(InetAddress.getLocalHost(),8086);
        } catch (Exception e) {

        }
    }

    public void clientStream() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            String message = "";
            while ((line = bufferedReader.readLine()) != null) {
                message += line;
            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            inputStream.close();
            outputStream.close();
            System.out.println("clientSocekt send => " + message + socket.getLocalSocketAddress());
            socket.close();
        } catch (Exception e) {

        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {

            }
        }

    }
}
