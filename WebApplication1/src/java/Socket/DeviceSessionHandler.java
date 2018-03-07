/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Database.Message;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/**
 *
 * @author minhdao
 */

@ApplicationScoped
public class DeviceSessionHandler {

    private final Set<Session> sessions = new HashSet<>();
    private final Set<Message> msgs = new HashSet<>();
    
    public void addSession(Session session){
        sessions.add(session);
        for (Message m : msgs) {
            JsonObject addMessage = createAddMessage(m);
            sendToSession(session, addMessage);
        }
    }
    
    public void removeSession(Session session){
        sessions.remove(session);
    }
    
    public List<Message> getMessages() {
        return new ArrayList<>(msgs);
    }

    public void addMessage(Message m) {
        JsonObject addMessage = createAddMessage(m);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeMessage(int id) {
        Message m = getMessageById(id);
        if (m != null) {
            msgs.remove(m);
        }
    }

    private Message getMessageById(int id) {
        for (Message m : msgs) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Message m) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("content", m.getContent())
                .add("senderId", m.getSenderId().getId())
                .build();
        return addMessage;

    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
