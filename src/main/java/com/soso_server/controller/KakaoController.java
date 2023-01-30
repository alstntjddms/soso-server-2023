package com.soso_server.controller;

import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    /**
     * accesCode로 kakao정보를 등록한다.
     * @param authorize_code
     * @return 암호화된 등록된 id
     */
    @PostMapping("/kakao")
    public ResponseEntity<String> getService(@RequestBody String authorize_code) {
        try {
            System.out.println("authorize_code = " + authorize_code);
            System.out.println("================================11");
            return new ResponseEntity<>(kakaoService.getService(authorize_code), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

}
