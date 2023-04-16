package com.soso_server.service;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.KakaoRAO;
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

    MessageRAO rao;
    @Autowired
    KakaoRAO kakaoRAO;
    @Autowired
    KakaoService kakaoService;
    private int refreshTokenCheckId = 0;
    public int count = 0;

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    public void setRao(MessageRAO rao) {
        this.rao = rao;
    }

    public int sendAllMessage(String message){
        try{
            List<KakaoDTO> kakaoDTOS = kakaoRAO.findKakaoAll();
            for(KakaoDTO kakaoDTO : kakaoDTOS){
                if(kakaoDTO.isKakaoMsgYn() == true){
                    sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
                            message, kakaoDTO.getId());
                }
            }
            return count;
        }finally {
            count = 0;
        }
    }

    public boolean sendMessage(String accessToken, String refreshToken, String message, int id) {
        try {
            URL url = new URL("https://kapi.kakao.com/v2/api/talk/memo/default/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);

            String parameters = "template_object=" + URLEncoder.encode("{\"object_type\":\"text\","
                            + "\"text\":\"" + message + "\","
                            + "\"link\":{\"web_url\":\"https://plater.kr\"},"
                            + "\"button_title\":\"Button\","
                            + "\"buttons\":[{\"title\":\"편지 읽으러 가기\",\"link\":{\"web_url\":\"https://plater.kr\"}}]}",
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
                return true;
            // access_Token 만료시 refresh_Token으로 access_Token 새로 가져옴
            // 무한 재귀 방지
            } else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED && refreshTokenCheckId != id) {
                sendMessage(kakaoService.refreshAccessToken(refreshToken), refreshToken, message, id);
                refreshTokenCheckId = id;
                logger.info("refreshAccessToken kakaoDTO.getId() = " + id);
            }else {
                System.out.println("HTTP request failed: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            refreshTokenCheckId = 0;
        }
        return false;
    }
}
