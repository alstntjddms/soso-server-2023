package com.soso_server.service;

import com.soso_server.dto.AuthDTO;
import com.soso_server.ra.itf.AuthRAO;
import com.soso_server.service.itf.AuthService;
import com.soso_server.utils.JWT.JwtUtils;
import io.jsonwebtoken.Claims;
import org.jboss.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServiceImpl implements AuthService {

    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class);

    AuthRAO rao;

    public void setRao(AuthRAO rao) {
        this.rao = rao;
    }

    @Override
    public Boolean createCookie(String code, HttpServletResponse response) {
        try{
            logger.info("[checkCode] Start");

            AuthDTO authDTO = rao.checkCode(code);
            if(authDTO == null){
                throw new Exception("잘못된 code");
            }
            // JWT 생성
            String jwtToken = JwtUtils.generateJwtToken(authDTO.getName(), authDTO.getCode(), authDTO.getAuthKey());

            System.out.println("jwtToken = " + jwtToken);
            System.out.println("jwtToken = " + JwtUtils.validateJwtToken(jwtToken));
            System.out.println("jwtToken = " + JwtUtils.getUsernameFromJwtToken(jwtToken));

            Cookie cookie = new Cookie("sosoJwtToken", jwtToken);
            cookie.setMaxAge(3600); // 쿠키 만료 시간 (1시간)
            cookie.setPath("/"); // 쿠키의 유효 경로 설정 (전체 경로에 대해 유효)
            response.addCookie(cookie);

            logger.info("[checkCode] End");
            return true;
        }catch (Exception e){
            logger.warn("[checkCode] Exception = " + e.getMessage());
        }
        logger.info("[checkCode] End");
        return false;
    }

    @Override
    public Boolean checkJwtToken(HttpServletRequest request) {
        try{
            logger.info("[checkJwtToken] Start");

            System.out.println("request.getHeader(\"sosoJwtToken\") = " + request.getHeader("sosoJwtToken"));
            // 헤더에서 JWT 토큰 추출
            String authorizationHeader = request.getHeader("sosoJwtToken");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                // JWT 토큰이 없거나 형식이 맞지 않는 경우 예외 처리
                throw new RuntimeException("Invalid JWT token");
            }
            String token = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분 추출
            System.out.println("token = " + token);
            if(!JwtUtils.validateJwtToken(token)){
                throw new Exception("유효하지 않은 토큰");
            }

            Claims claims = JwtUtils.getJwtTokenInfo(token);
            String name = (String) claims.get("name");
            String code = (String) claims.get("code");
            String authkey = (String) claims.get("authkey");

            System.out.println("claims = " + claims);
            AuthDTO authDTO = rao.checkCode(code);
            if(authDTO == null){
                throw new Exception("잘못된 code");
            }

            if(!name.equals(authDTO.getName()) && !code.equals(authDTO.getCode()) && !authkey.equals(authDTO.getAuthKey())){
                throw new Exception("유효하지 않은 토큰 정보");
            }

            logger.info("[checkJwtToken] End");
            return true;
        }catch (Exception e){
            logger.warn("[checkJwtToken] Exception = " + e.getMessage());
        }
        logger.info("[checkJwtToken] End");
        return false;
    }

}
