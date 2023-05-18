package com.soso_server.controller;

import com.soso_server.service.itf.MessageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private static final Logger logger = Logger.getLogger(MemberController.class);

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
}