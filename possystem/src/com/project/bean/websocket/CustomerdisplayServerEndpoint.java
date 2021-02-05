package com.project.bean.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.faces.bean.SessionScoped;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;



@SessionScoped
@ServerEndpoint(
value = "/customerDisplayServerEndpoint",
encoders = { JsonmessageEncoder.class },
decoders= {JsonmessageDecoder.class}
)
public class CustomerdisplayServerEndpoint {
	
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	private static HashMap<Session,String> sessionMap=new HashMap<Session,String>();
	
	public CustomerdisplayServerEndpoint() {
	}
	
	@OnOpen
	public void onOpen(Session session) {
		queue.add(session);
		sessionMap.put(session, "");
	}
	
	@OnMessage
	public void onMessage(CustomerdisplayMessageModel message, Session session) throws IOException, EncodeException {
		CustomerdisplayMessageModel msg=new CustomerdisplayMessageModel();
		msg.setMessageType(message.getMessageType());
		if(message.getMessageType().equalsIgnoreCase("INIT DISPLAY")) {
			sessionMap.put(session,message.getTerminalName());
		}else if(message.getMessageType().equalsIgnoreCase("INIT POS")) {
			sessionMap.put(session,message.getTerminalName());
		}else {
			Gson gson = new Gson();
			String jsonString = gson.toJson(message);
			for (Entry<Session, String> entry : sessionMap.entrySet()) {
				 if(entry.getValue().equalsIgnoreCase(message.getTerminalName()) && entry.getKey()!=session) {
					 Session display=entry.getKey();
					 try {
						display.getBasicRemote().sendText(jsonString);
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
			 }
		}
	}

	@OnError
	public void onError(Session session,Throwable e) {
		
	}
	
	@OnClose
	public void onClose(Session session) {
		queue.remove(session);
		sessionMap.remove(session);
		//System.out.printf("Session closed with id: %s%n", session.getId());
	}
	
}
