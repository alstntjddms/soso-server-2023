package com.soso_server.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.soso_server.service.itf.MessageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "https://plater.kr:8888") // 허용할 오리진을 지정
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    /**
     * 카카오 메세지 테스트
     */
    @GetMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message,@RequestParam("buttonTitle") String buttonTitle) {
        try {
            logger.info("[sendMessage] MessageController.sendMessage");
            logger.info("[sendMessage] message = " + message);
            logger.info("[sendMessage] buttonTitle = " + buttonTitle);
            return new ResponseEntity<Integer>(messageService.sendAllMessage(message, buttonTitle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/message/feedback/{userId}")
    public ResponseEntity<String> feedBackMessage(@PathVariable String userId, @RequestBody String feedBack) {
        try {
            logger.info("[feedBackMessage] MessageController.sendMessage");
            logger.info("[feedBackMessage] userId = " + userId);
            logger.info("[feedBackMessage] feedBack = " + feedBack);
            return new ResponseEntity<String>(messageService.feedBackMessage(userId, feedBack), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    @Autowired
    private FirebaseConfig firebaseConfig;

    @PostMapping("/notification")
    public String notificationTest(@RequestBody String token) throws FirebaseMessagingException {
        testNoti(token);
        return "알림전송..";
    }

    public void testNoti(String token) throws FirebaseMessagingException {
        try {
            // 7초(7000 밀리초) 동안 딜레이
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            // InterruptedException이 발생하면 예외 처리 코드를 작성할 수 있습니다.
            e.printStackTrace();
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // 날짜 및 시간 형식 지정

        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle("알림 테스트...").setBody("전송시간[" + now.format(formatter)+"]")
                                .build()
                )
                .build();

        // 알림 보내기
        String response = FirebaseMessaging.getInstance().send(message);
    }

}