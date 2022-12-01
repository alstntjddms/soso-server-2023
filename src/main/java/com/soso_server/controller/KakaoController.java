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

    /**
     * accesCode로 kakao정보를 등록한다.
     * @param accessCode
     * @return 암호화된 등록된 id
     */
    @PostMapping("/kakao")
    public String getService(@RequestBody String accessCode) {
        return kakaoService.getService(accessCode);

    }
}
