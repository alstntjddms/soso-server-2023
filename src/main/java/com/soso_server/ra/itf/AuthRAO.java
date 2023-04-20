package com.soso_server.ra.itf;

import com.soso_server.dto.AuthDTO;
import com.soso_server.dto.KakaoDTO;

import java.util.List;

public interface AuthRAO {
    public List<AuthDTO> selectAuthAll(String code);

}
