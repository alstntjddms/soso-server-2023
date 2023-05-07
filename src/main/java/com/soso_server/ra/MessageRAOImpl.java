package com.soso_server.ra;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.MessageRAO;

import java.util.List;

public class MessageRAOImpl implements MessageRAO {

    private MessageRAO mapper;

    public void setMapper(MessageRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<KakaoDTO> findKakaoByDateExpired() {
        return mapper.findKakaoByDateExpired();
    }
}
