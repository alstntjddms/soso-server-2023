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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public ResponseEntity<?> createCookie(@RequestBody String code, HttpServletResponse response){
        try {
            logger.info("[checkCode] AuthController.checkAuth");

            logger.info("[checkCode] code = " + code);
            return new ResponseEntity<Boolean>(authService.createCookie(code, response), HttpStatus.OK);
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

            if(!authService.checkJwtToken(request)){
                throw new Exception("토큰이 잘못됨");
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

            if(!authService.checkJwtToken(request)){
                throw new Exception("토큰이 잘못됨");
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

            if(!authService.checkJwtToken(request)){
                throw new Exception("토큰이 잘못됨");
            }

            return new ResponseEntity<List<LetterDTO>>(letterService.findLetterAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 관리자를 등록한다. gpt 테스트용
     */
    @PostMapping("/manager")
    public ResponseEntity<String> registerAuth(@RequestBody AuthDTO authDTO){
        try {
            logger.info("[registerAuth] AuthController.registerAuth");
            logger.info(authDTO);
            return new ResponseEntity<String>(authService.register(authDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 관리자를 수정한다. gpt 테스트용
     */
    @PatchMapping("/manager")
    public ResponseEntity<String> updateAuth(@RequestBody AuthDTO authDTO){
        try {
            logger.info("[registerAuth] AuthController.updateAuth");
            return new ResponseEntity<String>(authService.update(authDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 관리자를 조회한다.
     */
    @GetMapping("/managerall")
    public ResponseEntity<?> findManagerAll(){
        try {
            logger.info("[findManagerAll] AuthController.findManagerAll");
            return new ResponseEntity<List<AuthDTO>>(authService.findManagerAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }


}
