package com.soso_server.service.itf;

public interface MessageService {

    public int sendAllMessage(String message);

    public boolean sendMessage(String accessToken, String refreshToken, String message, int id);
}

