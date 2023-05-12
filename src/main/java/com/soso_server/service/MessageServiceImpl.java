package com.soso_server.service;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.ra.itf.MessageRAO;
import com.soso_server.service.itf.KakaoService;
import com.soso_server.service.itf.MessageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Override
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

            String templateObject = "{\"object_type\":\"text\","
                    + "\"text\":\"" + message + "\","
                    + "\"link\":{\"web_url\":\"https://plater.kr\","
                    + "\"mobile_web_url\":\"https://plater.kr\"},"
                    + "\"button_title\":\"" + buttonTitle + "\"}";
                    
            String parameters = "template_object=" + URLEncoder.encode(templateObject, StandardCharsets.UTF_8);

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
                logger.warn("[sendMessage] second HTTP request failed: " + responseCode);
            }else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                refreshTokenCheckId = refreshToken;
                sendMessage(kakaoService.refreshAccessToken(refreshToken), refreshToken, message, buttonTitle);
                logger.info("[sendMessage] refreshAccessToken refreshToken = " + refreshToken);
            }else {
                logger.warn("[sendMessage] HTTP request failed: " + responseCode);
            }
            refreshTokenCheckId = "";

            logger.info("[sendMessage] End");
            return false;
        } catch (IOException e) {
            logger.warn("[sendMessage] IOException" +e.getMessage());
        }
        return false;
    }

    @Override
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
            logger.warn("[sendAllMessage] Exception = " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void sendMessageByLetterCount(int userId) {
        logger.info("[sendMessageByLetterCount] Start");

        logger.info("[sendMessageByLetterCount] userId = " + userId);
        int letterCount = memberRAO.findMemberByLetterCount(userId);
        if(letterCount == 1 || letterCount == 9 || letterCount == 18 || letterCount == 27 || letterCount == 36) {
            MemberDTO memberDTO = memberRAO.findMemberByUserId(userId);
            KakaoDTO kakaoDTO = kakaoRAO.findOneKakaoById(memberDTO.getId());
            sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
            "[PL@TER]" + memberDTO.getUserNickName() + "님!\\n" +  letterCount + "번째 편지가 도착했어요!", "더 공유하러 가기");

            logger.info("[sendMessageByLetterCount] memberDTO.getUserNickName() = " + memberDTO.getUserNickName() 
                        + "에게" + letterCount + "번째 편지가 도착했다고 알림.");
        }
        
        logger.info("[sendMessageByLetterCount] End");
    }

    @Override
    @Scheduled(cron = "0 0 */1 * * *")
    public void sendMessageEveryHour() {
        try{
            // 행성 만료 메세지 전송
            List<KakaoDTO> kakaoDTOS = rao.findKakaoByDateExpired();
            if(kakaoDTOS.size() > 0){
                logger.info("[sendMessageEveryHour] 행성 만료 메세지 Start");
                // 만료된 사용자
                for(KakaoDTO kakaoDTO : kakaoDTOS){
                    String nickName = memberRAO.findMemberById(kakaoDTO.getId()).getUserNickName();
                    sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
                            "[PL@TER] " + nickName + "님!\\n 모든 편지가 도착했어요!", "지금 확인하러 가기");

                    logger.info("[sendMessageEveryHour] nickName = " + nickName + "에게 모든 편지가 도착했다고 알림.");
                }
                logger.info("[sendMessageEveryHour] 행성 만료 메세지 End");
            }

            // 회원가입 후 행성을 개설하지 않은지 3일 후
            kakaoDTOS = rao.findKakaoToAfterRegisterByNotNewOpenDate();
            if(kakaoDTOS.size() > 0){
                logger.info("[sendMessageEveryHour] 회원가입 후 행성을 개설하지 않은지 3일 후 Start");
                // 만료된 사용자
                for(KakaoDTO kakaoDTO : kakaoDTOS){
                    String nickName = memberRAO.findMemberById(kakaoDTO.getId()).getUserNickName();
                    sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
                            "[PL@TER] " + nickName + "님 반가워요\\n행성을 개설해야 메세지를 받을 수 있어요!", "행성 개설 하기");

                    logger.info("[sendMessageEveryHour] nickName = " + nickName + "에게 회원가입 후 행성을 개설하지 않은지 3일이 지났다고 알림.");
                }
                logger.info("[sendMessageEveryHour] 회원가입 후 행성을 개설하지 않은지 3일 후 End");
            }

            // 행성을 만료한 후 새로 개설하지 않은지 3일 후
            kakaoDTOS = rao.findKakaoToDateExpiredByNotNewOpenDate();
            if(kakaoDTOS.size() > 0){
                logger.info("[sendMessageEveryHour] 행성을 만료한 후 접속하지 않은지 3일 후 Start");
                // 만료된 사용자
                for(KakaoDTO kakaoDTO : kakaoDTOS){
                    String nickName = memberRAO.findMemberById(kakaoDTO.getId()).getUserNickName();
                    sendMessage(kakaoDTO.getKakaoAccessToken(), kakaoDTO.getKakaoRefreshToken(),
                            "[PL@TER] " + nickName + "님 편지는 잘 받으셨나요?\\n다시 편지를 받으려면 행성을 개설해야해요!", "행성 개설하기");

                    logger.info("[sendMessageEveryHour] nickName = " + nickName + "에게 행성 만료 후 개설하지 않은지 3일이 자났다고 알림.");
                }
                logger.info("[sendMessageEveryHour] 행성을 만료한 후 접속하지 않은지 3일 후 End");
            }

        }catch (Exception e){
            logger.warn("[sendMessageEveryHour] Exception = " + e.getMessage());
        }
    }


}
