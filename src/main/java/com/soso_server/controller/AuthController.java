package com.soso_server.controller;

import com.soso_server.dto.AuthDTO;
import com.soso_server.service.itf.AuthService;
import com.soso_server.service.itf.KakaoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    /**
     * authCheck구현
     */
    @PostMapping("/auth")
    public String checkAuth(@RequestBody String code){
        try {
            logger.info("[checkAuth] AuthController.checkAuth");

            logger.info("[checkAuth] authCode = " + code);
            List<AuthDTO> authDTOS = authService.selectAuthAll(code);
            for(AuthDTO a : authDTOS){
                if(code.equals(a.getCode())){
                    return a.getAuthKey();
                }
            }

            logger.info("[checkAuth] Exception 로그인 실패, 잘못된 authCode");
            return "";
        }catch (Exception e){
            logger.info("[checkAuth] Exception = " + e.getMessage());
            return "";
        }
    }


}
