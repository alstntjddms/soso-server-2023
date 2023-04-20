package com.soso_server.service.itf;

import com.soso_server.dto.AuthDTO;

import java.util.List;

public interface AuthService {

    public List<AuthDTO> selectAuthAll(String code);

}

