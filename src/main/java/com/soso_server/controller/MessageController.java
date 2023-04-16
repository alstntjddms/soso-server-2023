package com.soso_server.controller;

import com.soso_server.service.itf.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 카카오 메세지 테스트
     */
    @GetMapping("/message")
    public ResponseEntity<Integer> sendMessage(@RequestParam("message") String message,@RequestParam("buttonTitle") String buttonTitle) {
        try {
            System.out.println("MessageController.sendMessage");
            System.out.println("message = " + message);
            return new ResponseEntity<>(messageService.sendAllMessage(message, buttonTitle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }
}