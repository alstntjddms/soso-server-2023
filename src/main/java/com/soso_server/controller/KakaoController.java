package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @PostMapping("/kakao")
    public String getService(@RequestBody String accessCode) {
        return kakaoService.getService(accessCode);

    }
}
