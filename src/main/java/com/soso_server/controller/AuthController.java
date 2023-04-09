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
    public String checkAuth(@RequestBody String authCode){
        try {
            System.out.println("AuthController.checkAuth");
            if(authCode.equals("7505") || authCode.equals("3599")){
                return "15688974896465156213";
            }else{
                return "";
            }
        }catch (Exception e){
            return "";
        }
    }


}
