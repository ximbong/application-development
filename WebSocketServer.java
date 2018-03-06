/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import Database.TaskMessage;

/**
 *
 * @yadavr
 */

 @ApplicationScoped
 @ServerEndpoint("/actions/{messageId}")

 public class WebSocketServer{

    private static final Set<Session> sessions = new HashSet<>();
    private static List<TaskMessage> taskmsgs = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("add session");
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("remove session");
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    public static void sendAll(String message, String action){
        try(JsonReader reader = Json.createReader(new StringReader(message))){
            JsonObject jsonMessage = reader.readObject();
            String returnMessage = "";
            TaskMessage taskmsg;
            int messageId = 0;
            switch(action){
                case "send":
                    taskmsg = new TaskMessage();
                    taskmsg.setMessageId(jsonMessage.getInt("id"));
                    taskmsg.setDescription(jsonMessage.getString("description"));
                    taskmsg.setLocation(jsonMessage.getString("location"));
                    taskmsg.setDetails(jsonMessage.getString("details"));
                    returnMessage = addTaskMsg(taskmsg);
                    messageId = taskmsg.getMessageId();
                    break;
            }
            synchronized(sessions) {
                for(Session session:sessions){
                    if(session.isOpen()){
                        String uri = session.getRequestURI().toString();
                        char msgIdFromURI = uri.charAt(uri.length() - 1);
                        if(Character.getNumericValue(msgIdFromURI) == messageId || Character.getNumericValue(msgIdFromURI) == 1){
                            session.getAsyncRemote().sendText(returnMessage);
                        }
                    }
                }
            }
        }
    }

    public static WebSocketServer getInstance() {
        return WebSocketServerHolder.INSTANCE;
    }
    
    private static class WebSocketServerHolder {

        private static final WebSocketServer INSTANCE = new WebSocketServer();
    }
     
    public static void SetTaskMsg(List<TaskMessage> allTaskmsgs){
        System.out.println("set task messages");
        for(TaskMessage tm : allTaskmsgs){
            taskmsgs.add(tm);
        }
    }
     
     public static String addTaskMsg(TaskMessage taskmsg){
         System.out.println("add task message");
         taskmsgs.add(taskmsg);
         JsonObject addMessage;
         addMessage = createMessage(taskmsg, "add");
         return addMessage.toString();
     }
     
     private static int getTaskIndex(int id){
         int index = -1;
         for(int i = 0; i<taskmsgs.size();i++){
             if(taskmsgs.get(i).getMessageId() == id){
                 index = i;
             }
         }
         System.out.println("index: " + index);
         return index;
     }
     
     private static JsonObject createMessage(TaskMessage taskmsg, String action){
         JsonProvider provider = JsonProvider.provider();
         JsonObject addMessage = provider.createObjectBuilder()
             .add("action", action)
             .add("id", taskmsg.getMessageId())
             .add("description", taskmsg.getDescription() != null? taskmsg.getDescription(): "")
             .add("location", taskmsg.getLocation())
             .add("details", taskmsg.getDetails())
             .build();
         return addMessage;
     }




 }