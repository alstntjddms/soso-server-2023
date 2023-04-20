package com.soso_server.ra;

import com.soso_server.dto.AuthDTO;
import com.soso_server.ra.itf.AuthRAO;
import com.soso_server.ra.itf.MessageRAO;

import java.util.List;

public class AuthRAOImpl implements AuthRAO {
    private AuthRAO mapper;
    public void setMapper(AuthRAO mapper) {
            this.mapper = mapper;
        }

    @Override
    public List<AuthDTO> selectAuthAll(String code) {
        return mapper.selectAuthAll(code);
    }
}
