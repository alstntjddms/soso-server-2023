package com.soso_server.service.itf;

public interface MessageService {

    /**
     * 기본 알림 전송 로직
     * @param accessToken
     * @param refreshToken
     * @param message
     * @param buttonTitle
     * @return
     */
    public boolean sendMessage(String accessToken, String refreshToken, String message, String buttonTitle);

    /**
     * 모든 사용자에게 메세지 전송
     * @param message
     * @param buttonTitle
     * @return
     */
    public int sendAllMessage(String message, String buttonTitle);

    /**
     * 특정 갯수를 받은 사용자에게 메세지 전송
     * @param userId
     */
    public void sendMessageByLetterCount(int userId);

    /**
     * 1시간마다 배치를 돌며 알림 전송
     */
    public void sendMessageEveryHour();

}

