package com.soso_server.service;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.ra.itf.MessageRAO;
import com.soso_server.service.itf.KakaoService;
import com.soso_server.service.itf.MessageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    MessageRAO rao;

    @Autowired
    KakaoRAO kakaoRAO;

    @Autowired
    MemberRAO memberRAO;

    @Autowired
    KakaoService kakaoService;

    private String refreshTokenCheckId = "";

    public int count = 0;

    public void setRao(MessageRAO rao) {
        this.rao = rao;
    }

    public int sendAllMessage(String message, String buttonTitle){
        try{
            logger.info("[sendAllMessage] Start");

            List<KakaoDTO> kakaoDTOS = kakaoRAO.findKakaoAll();
            for(KakaoDTO kakaoDTO : kakaoDTOS){
                if(kakaoDTO.isKakaoMsgYn() == true){
                    sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(), message, buttonTitle);
                }
            }
            
            logger.info("[sendAllMessage] 총 " + count + "건의 메세지 전송이 성공.");
            
            count = 0;
            logger.info("[sendAllMessage] End");
            return count;
        }catch(Exception e){
            logger.info("[sendAllMessage] Exception = " + e.getMessage());
        }
        return 0;
    }

    public boolean sendMessage(String accessToken, String refreshToken, String message, String buttonTitle) {
        try {
            logger.info("[sendMessage] Start");

            logger.info("[sendMessage] accessToken = " + accessToken);
            logger.info("[sendMessage] refreshToken = " + refreshToken);
            logger.info("[sendMessage] message = " + message);
            logger.info("[sendMessage] buttonTitle = " + buttonTitle);

            URL url = new URL("https://kapi.kakao.com/v2/api/talk/memo/default/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);

            String parameters = "template_object=" + URLEncoder.encode("{\"object_type\":\"text\","
                            + "\"text\":\"" + message + "\","
                            + "\"link\":{\"web_url\":\"https://plater.kr\","
                            + "\"mobile_web_url\":\"https://plater.kr\"},"
                            + "\"button_title\":\"" + buttonTitle + "\"}",

//                            + "\"buttons\":[{\"title\":\"" + buttonTitle + "\",\"link\":{\"web_url\":\"https://plater.kr\"}}]}",
                    StandardCharsets.UTF_8);

            byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(postData);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                count++;
                refreshTokenCheckId = "";

                logger.info("[sendMessage] HttpURLConnection.HTTP_OK sendMessage 성공");
                return true;
            // access_Token 만료시 refresh_Token으로 access_Token 새로 가져옴
            // 무한 재귀 방지
            }else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED && refreshTokenCheckId == refreshToken){
                logger.info("[sendMessage] second HTTP request failed: " + responseCode);
            }else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                refreshTokenCheckId = refreshToken;
                sendMessage(kakaoService.refreshAccessToken(refreshToken), refreshToken, message, buttonTitle);
                logger.info("[sendMessage] refreshAccessToken refreshToken = " + refreshToken);
            }else {
                logger.info("[sendMessage] HTTP request failed: " + responseCode);
            }
            refreshTokenCheckId = "";

            logger.info("[sendMessage] End");
            return false;
        } catch (IOException e) {
            logger.info("[sendMessage] IOException" +e.getMessage());
        }
        return false;
    }

    @Override
    public void sendMessageByLetterCount(int userId) {
        logger.info("[sendMessageByLetterCount] Start");

        logger.info("[sendMessageByLetterCount] userId = " + userId);
        int letterCount = memberRAO.findMemberByLetterCount(userId);
        if(letterCount == 1 || letterCount == 9 || letterCount == 18 || letterCount == 27 || letterCount == 36) {
            KakaoDTO kakaoDTO = kakaoRAO.findOneKakaoById(memberRAO.findMemberByUserId(userId).getId());
            sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
            "PL@TER:" + letterCount + "번째 편지가 도착했어요!", "더 공유하러 가기");
        }
        logger.info("[sendMessageByLetterCount] userId = " + userId + "에게" + letterCount + "번재 편지가 도착했다고 알림.");

        logger.info("[sendMessageByLetterCount] End");
    }


}
