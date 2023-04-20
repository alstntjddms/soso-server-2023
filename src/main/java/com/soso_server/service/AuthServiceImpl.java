package com.soso_server.service;

import com.soso_server.dto.AuthDTO;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.AuthRAO;
import com.soso_server.service.itf.AuthService;
import com.soso_server.service.itf.MessageService;
import org.jboss.logging.Logger;

import java.util.List;

public class AuthServiceImpl implements AuthService {

    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class);

    AuthRAO rao;

    public void setRao(AuthRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<AuthDTO> selectAuthAll(String code) {
        try{
            logger.info("[selectAuthAll] Start");

            List<AuthDTO> authDTOS = rao.selectAuthAll(code);
            if(authDTOS.size() > 0){
                logger.info("[selectAuthAll] End");
                return authDTOS;
            }else{
                throw new Exception("데이터 없음");
            }
        }catch (Exception e){
            logger.info("[selectAuthAll] Exception = " + e.getMessage());
        }
        logger.info("[selectAuthAll] End");
        return null;
    }

}
