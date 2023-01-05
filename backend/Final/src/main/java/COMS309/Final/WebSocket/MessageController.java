package COMS309.Final.WebSocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@ServerEndpoint(value = "/chat/{username}")
public class MessageController {

    private static MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {

        logger.info("Open");

        sessionUsernameMap.put(session,username);
        usernameSessionMap.put(username,session);

        sendMessageToUser(username, getChatHistory());

        broadcast(username + ": has joined the chat");

    }

    private void sendMessageToUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        }
        catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }


    @OnMessage
    public void onMessage(Session session, String message) throws IOException{
        logger.info("Message");
        String username = sessionUsernameMap.get(session);

        if (message.startsWith("@")) {
            String splitUsername = message.split(" ")[0].substring(1);

            sendMessageToUser(splitUsername, "[DM] " + username + ": " + message);
            sendMessageToUser(username, "[DM] " + username + ": " + message);
        }
        else {
            broadcast(username + ": " + message);
        }

        broadcast(username + ":" + message);

        messageRepository.save(new Message(username, message));
    }

    public void broadcast(String message){
        sessionUsernameMap.forEach((session, s) -> {
            try{
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e){
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }
        });
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        logger.info("Error");
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Close");

        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        broadcast(username + "disconnected");
    }

    private String getChatHistory(){
        List<Message> messages = messageRepository.findAll();

        StringBuilder sb = new StringBuilder();

        if(messages != null && messages.size() != 0) {

            for (Message message : messages) {

                sb.append(message.getUsername() + ": " + message.getContent() + "\n");

            }
        }

        return sb.toString();

    }

}
