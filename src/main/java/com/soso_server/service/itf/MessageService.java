package com.soso_server.service.itf;

public interface MessageService {

    public int sendAllMessage(String message, String buttonTitle);

    public boolean sendMessage(String accessToken, String refreshToken, String message, String buttonTitle);

    public void sendMessageByLetterCount(int userId);

}

