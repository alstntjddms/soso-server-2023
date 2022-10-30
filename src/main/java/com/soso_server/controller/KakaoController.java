package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://13.209.184.10:8080", allowedHeaders = "*")
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @PostMapping("/kakao")
    public String getService(@RequestBody String accessCode) {
        System.out.println("accessCode = " + accessCode);
        return kakaoService.getService(accessCode);

    }
}
