/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Database.Message;
import Database.Users;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author minhdao
 */

@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {
    
    @Inject
    private DeviceSessionHandler sessionHandler;
    
    @OnOpen
    public void open(Session session){
        sessionHandler.addSession(session);
    }
    
    @OnClose
    public void close(Session session){
        sessionHandler.removeSession(session);
    }
    
    @OnError
    public void onError(Throwable error){
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }
    
    @OnMessage
    public void handleMessage(String message, Session session){
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
                Message m = new Message();
                m.setContent(jsonMessage.getString("content"));
                Users u = new Users();
                u.setId(Integer.parseInt(jsonMessage.getString("senderId")));
                m.setSenderId(u);
                sessionHandler.addMessage(m);
        }
    }
}
