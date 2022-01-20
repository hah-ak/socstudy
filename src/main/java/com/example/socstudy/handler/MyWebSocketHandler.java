package com.example.socstudy.handler;

//import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

public class MyWebSocketHandler extends BinaryWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session = new ConcurrentWebSocketSessionDecorator(session,1000,8192);
    };

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 아직은 놔둠.
        System.out.println(message.getPayload());
        try {
            session.sendMessage(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
