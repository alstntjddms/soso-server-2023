package com.soso_server.ra.itf;

import com.soso_server.dto.AuthDTO;

import java.util.List;

public interface AuthRAO {

    public AuthDTO checkCode(String code);

    public AuthDTO checkAuthKey(String authKey);

    /**
     * 관리자 생성
     */
    public void register(AuthDTO authDTO);

    /**
     * 관리자 수정
     */
    public void update(AuthDTO authDTO);

    /**
     * 관리자 조회
     */
    public List<AuthDTO> findManagerAll();

}
