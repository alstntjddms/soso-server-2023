package com.soso_server.service;

import com.soso_server.dto.AuthDTO;
import com.soso_server.ra.itf.AuthRAO;
import com.soso_server.service.itf.AuthService;
import org.jboss.logging.Logger;

public class AuthServiceImpl implements AuthService {

    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class);

    AuthRAO rao;

    public void setRao(AuthRAO rao) {
        this.rao = rao;
    }

    @Override
    public String checkCode(String code) {
        try{
            logger.info("[checkCode] Start");

            AuthDTO authDTO = rao.checkCode(code);
            if(authDTO == null){
                throw new Exception("잘못된 code");
            }
            
            logger.info("[checkCode] End");
            return authDTO.getAuthKey();
        }catch (Exception e){
            logger.warn("[checkCode] Exception = " + e.getMessage());
        }
        logger.info("[checkCode] End");
        return null;
    }

    @Override
    public String checkAuthKey(String authKey) {
        try{
            logger.info("[checkAuthKey] Start");
            AuthDTO authDTO = rao.checkAuthKey(authKey);
            if(authDTO == null){
                throw new Exception("잘못된 code");
            }

            logger.info("[checkAuthKey] End");
            return authDTO.getCode();
        }catch (Exception e){
            logger.warn("[checkAuthKey] Exception = " + e.getMessage());
        }
        logger.info("[checkAuthKey] End");
        return null;
    }

}
