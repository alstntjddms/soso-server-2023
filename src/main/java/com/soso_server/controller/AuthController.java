package com.soso_server.controller;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class);

    /**
     * authCheck구현
     */
    @PostMapping("/auth")
    public String checkAuth(@RequestBody String authCode){
        try {
            logger.info("[checkAuth] AuthController.checkAuth");
            logger.info("[checkAuth] authCode = " + authCode);
            if(authCode.equals("7505") || authCode.equals("3599")){
                return "15688974896465156213";
            }else{
                logger.info("[checkAuth] Exception 로그인 실패, 잘못된 authCode");
                return "";
            }
        }catch (Exception e){
            logger.info("[checkAuth] Exception = " + e.getMessage());
            return "";
        }
    }


}
