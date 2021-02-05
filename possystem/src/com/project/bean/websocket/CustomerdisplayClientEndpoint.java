package com.project.bean.websocket;

import java.io.IOException;
import java.net.URI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;



@ManagedBean(name = "customerdisplayClientEndpoint")
@SessionScoped
@ClientEndpoint(
encoders = { JsonmessageEncoder.class },
decoders= {JsonmessageDecoder.class}
)
public class CustomerdisplayClientEndpoint {
	
	private MessageHandler messageHandler;
	
	private final String uri="ws://software:8080/possystem/customerDisplayServerEndpoint";
	
	private Session session;

	   public CustomerdisplayClientEndpoint(){
	      try{
	         WebSocketContainer container=ContainerProvider.
	            getWebSocketContainer();
	         container.connectToServer(this, new URI(uri));

	      }catch(Exception ex){

	      }
	   }

	   @OnOpen
	   public void onOpen(Session session){
	      this.session=session;
	      
	   }

	   @OnMessage
	   public void onMessage(String message, Session session){
	    
	   }
	   
	   public void sendmsg(String asd) {
		   try {
			session.getBasicRemote().sendText("ASDASD");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   public void sendMessage(CustomerdisplayMessageModel message) {
		   try {
			   CustomerdisplayMessageModel msg=new CustomerdisplayMessageModel();
			   msg=message;
				session.getBasicRemote().sendObject(msg);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }

	   
	   public void addMessageHandler(MessageHandler msgHandler) {
	        this.messageHandler = msgHandler;
	    }
	   public static interface MessageHandler {

	        public void handleMessage(String message);
	    }

}
