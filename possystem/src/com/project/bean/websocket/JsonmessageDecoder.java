package com.project.bean.websocket;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class JsonmessageDecoder implements Decoder.Text<CustomerdisplayMessageModel> {
	
    @Override
    public CustomerdisplayMessageModel decode(String message) {
        Gson gson = new Gson();
        CustomerdisplayMessageModel customerDisplayMessage = gson.fromJson(message, CustomerdisplayMessageModel.class);
        return customerDisplayMessage;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // do nothing.
    }

    @Override
    public void destroy() {
        // do nothing.
    }
}
