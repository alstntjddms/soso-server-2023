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
    public AuthDTO selectAuth(String code) {
        try{
            logger.info("[selectAuth] Start");

            AuthDTO authDTO = rao.selectAuth(code);
            return authDTO;
        }catch (Exception e){
            logger.info("[selectAuth] Exception = " + e.getMessage());
        }
        logger.info("[selectAuth] End");
        return null;
    }

    @Override
    public AuthDTO checkAuthKey(String authKey) {
        try{
            return rao.checkAuthKey(authKey);
        }catch (Exception e){
            logger.info("[checkAuthKey] Exception = " + e.getMessage());
        }
        return null;
    }

}
