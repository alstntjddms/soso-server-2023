package com.soso_server.controller;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    /**
     * authCheck구현
     */
    @PostMapping("/auth")
    public boolean checkAuth(@RequestBody String authCode){
        try {
            System.out.println("AuthController.checkAuth");
            if(authCode.equals("970917") || authCode.equals("1234")){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }


}
