package com.soso_server.ra;

import com.soso_server.dto.FeedBackDTO;
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

    @Override
    public List<KakaoDTO> findKakaoToDateExpiredByNotNewOpenDate() {
        return mapper.findKakaoToDateExpiredByNotNewOpenDate();    }

    @Override
    public List<KakaoDTO> findKakaoToAfterRegisterByNotNewOpenDate() {
        return mapper.findKakaoToAfterRegisterByNotNewOpenDate();
    }

    @Override
    public void feedBackMessage(FeedBackDTO feedBackDTO) {
        mapper.feedBackMessage(feedBackDTO);
    }
}
