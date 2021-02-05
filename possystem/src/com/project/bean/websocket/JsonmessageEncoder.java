package com.project.bean.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class JsonmessageEncoder implements Encoder.Text<CustomerdisplayMessageModel>{
	
	public String encode(CustomerdisplayMessageModel message) throws EncodeException {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        return json;
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
      
    }
    
}
