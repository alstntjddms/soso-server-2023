package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @PostMapping("/kakao")
    public String getService(@RequestBody String accessCode) {
//        String encodedString =
//                Base64.getEncoder().encodeToString(accessCode.getBytes());
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        String decodedString = new String(decodedBytes);

        return kakaoService.getService(accessCode);

    }
}
