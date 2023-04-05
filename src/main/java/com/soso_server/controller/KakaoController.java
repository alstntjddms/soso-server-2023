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
            System.out.println("KakaoController.getService");
            System.out.println("authorize_code = " + authorize_code);
            return new ResponseEntity<>(kakaoService.getService(authorize_code), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("withdrawkakao")
//    public ResponseEntity<String> withdrawKakao(@)


    /**
     * 리프레시토큰 테스트
     * 추후 삭제 예정
     */
    @PostMapping("/kakao/refresh")
    public ResponseEntity<String> refreshTokenTest(@RequestBody String refresh_token) {
        try {
            System.out.println("KakaoController.testService");
            System.out.println("refresh_token = " + refresh_token);
            return new ResponseEntity<>(kakaoService.refreshAccessToken(refresh_token), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원탈퇴 테스트
     */
    @PostMapping("/kakao/withdraw")
    public void withdrawTest(@RequestBody String access_token) {
        try {
            System.out.println("KakaoController.testService");
            System.out.println("access_token = " + access_token);
            kakaoService.withdraw(access_token);
        }catch (Exception e){

        }
    }

}
