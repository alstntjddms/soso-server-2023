package com.soso_server.controller;

import com.soso_server.service.itf.KakaoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class KakaoController {

    private static final Logger logger = Logger.getLogger(KakaoController.class);

    @Autowired
    KakaoService kakaoService;

    /**
     * accesCode로 kakao정보를 등록한다.
     * @param authorize_code
     * @return 암호화된 등록된 id
     */
    @PostMapping("/kakao")
    public synchronized ResponseEntity<String> getService(@RequestBody String authorize_code) {
        try {
            logger.debug("[getService] KakaoController.getService");
            logger.debug("[getService] authorize_code = " + authorize_code);
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
            logger.debug("[selectKakaoMsgYnByUserId] KakaoController.selectKakaoMsgYnByUserId");
            logger.debug("[selectKakaoMsgYnByUserId] userId = " + userId);
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
            logger.debug("[updateScopeCheck] KakaoController.getService");
            logger.debug("[updateScopeCheck] userId = " + userId);
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
            logger.debug("[revokeByUserId] KakaoController.revokeByUserId");
            logger.debug("[revokeByUserId] userId = " + userId);
            return new ResponseEntity<String>(kakaoService.revokeByUserId(userId), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원탈퇴
     */
    @PostMapping("/kakao/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody String userId) {
        try {
            logger.debug("[withdraw] KakaoController.withdraw");
            logger.debug("[withdraw] userId = " + userId);
            return new ResponseEntity<String>(kakaoService.withdraw(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

}
