package com.soso_server.service.itf;

import com.soso_server.dto.AuthDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AuthService {

    public Boolean createCookie(String code, HttpServletResponse response);

    /**
     * JwtToken 검증 로직
     * @param request
     * @return
     */
    public Boolean checkJwtToken(HttpServletRequest request);

    /**
     * 관리자 생성
     */
    public String register(AuthDTO authDTO);

    /**
     * 관리자 수정
     */
    public String update(AuthDTO authDTO);

    /**
     * 관리자 조회
     */
    public List<AuthDTO> findManagerAll();

}

