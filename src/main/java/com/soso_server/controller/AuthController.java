package com.soso_server.controller;

import com.soso_server.dto.AuthDTO;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.AuthService;
import com.soso_server.service.itf.KakaoService;
import com.soso_server.service.itf.LetterService;
import com.soso_server.service.itf.MemberService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @Autowired
    KakaoService kakaoService;

    @Autowired
    MemberService memberService;

    @Autowired
    LetterService letterService;

    /**
     * authCheck구현
     */
    @PostMapping("/auth")
    public ResponseEntity<String> checkCode(@RequestBody String code){
        try {
            logger.info("[checkCode] AuthController.checkAuth");

            logger.info("[checkCode] code = " + code);
            return new ResponseEntity<String>(authService.checkCode(code), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 카카오를 조회
     */
    @GetMapping("/kakaoall")
    public ResponseEntity<?> findKakaoAll(HttpServletRequest request){
        try {
            logger.info("[findKakaoAll] KakaoController.findKakaoAll");

            logger.info("[findKakaoAll] authKey = " + request.getQueryString());
            String code = authService.checkAuthKey(request.getQueryString());
            logger.info("[findKakaoAll] 호출한 code = " + code);

            if(code == null){
                logger.warn("[findKakaoAll] code = " + code);
                throw new Exception("code로 매니저를 찾을 수 없음");
            }
            return new ResponseEntity<List<KakaoDTO>>(kakaoService.findKakaoAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * 모든 멤버를 조회
     * @return List<MemberDTO>
     */
    @GetMapping("/memberall")
    public ResponseEntity<?> findMemberAll(HttpServletRequest request){
        try {
            logger.info("[findMemberAll] MemberController.findMemberAll");

            logger.info("[findMemberAll] authKey = " + request.getQueryString());
            String code = authService.checkAuthKey(request.getQueryString());
            logger.info("[findKakaoAll] 호출한 code = " + code);

            if(code == null){
                logger.warn("[findMemberAll] code = " + code);
                throw new Exception("code로 매니저를 찾을 수 없음");
            }
            return new ResponseEntity<List<MemberDTO>>(memberService.findMemberAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 Letter를 조회
     */
    @GetMapping("/letterall")
    public ResponseEntity<?> findLetterAll(HttpServletRequest request){
        try {
            logger.info("[findLetterAll] LetterController.findAllLetter");

            logger.info("[findLetterAll] authKey = " + request.getQueryString());
            String code = authService.checkAuthKey(request.getQueryString());
            logger.info("[findKakaoAll] 호출한 code = " + code);

            if(code == null){
                logger.warn("[findLetterAll] code = " + code);
                throw new Exception("code로 매니저를 찾을 수 없음");
            }
            return new ResponseEntity<List<LetterDTO>>(letterService.findLetterAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }


}
