package com.soso_server.controller;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
            return new ResponseEntity<String>(kakaoService.getService(authorize_code), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 kakaoMsgYn을 확인한다.
     * @param userId
     * @return boolean
     */
    @GetMapping("/kakao/msg/{userId}")
    public ResponseEntity<?> selectKakaoMsgYnByUserId(@PathVariable String userId) {
        try {
            System.out.println("KakaoController.selectKakaoMsgYnByUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<Boolean>(kakaoService.selectKakaoMsgYnByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * 동의항목을 눌렀다.
     * @param userId
     * @return void
     */
    @PostMapping("/kakao/scope")
    public ResponseEntity<String> updateScopeCheck(@RequestBody String userId) {
        try {
            System.out.println("KakaoController.getService");
            System.out.println("userId = " + userId);
            return new ResponseEntity<String>(kakaoService.updateScopeCheck(userId), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동의항목 (talk_message)를 철회한다.
     * @param userId
     */
    @PostMapping("kakao/revoke")
    public ResponseEntity<String> revokeByUserId(@RequestBody String userId){
        try {
            System.out.println("KakaoController.revokeByUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<String>(kakaoService.revokeByUserId(userId), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 리프레시토큰 테스트
     * 추후 삭제 예정
     */
    @PostMapping("/kakao/refresh")
    public ResponseEntity<String> refreshTokenTest(@RequestBody String refresh_token) {
        try {
            System.out.println("KakaoController.testService");
            System.out.println("refresh_token = " + refresh_token);
            return new ResponseEntity<String>(kakaoService.refreshAccessToken(refresh_token), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원탈퇴
     */
    @PostMapping("/kakao/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody String userId) {
        try {
            System.out.println("KakaoController.withdraw");
            System.out.println("userId = " + userId);
            return new ResponseEntity<String>(kakaoService.withdraw(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 관리용
     * 카카오 모든멤버 조회
     */
    @GetMapping("/kakaoall")
    public ResponseEntity<?> findKakaoAll(HttpServletRequest request){
        try {
            System.out.println("KakaoController.findKakaoAll");
            if(!request.getQueryString().equals("15688974896465156213")){
                return null;
            }
            return new ResponseEntity<List<KakaoDTO>>(kakaoService.findKakaoAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

}
