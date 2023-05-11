package com.soso_server.ra;

import com.soso_server.dto.KakaoDTO;
import com.soso_server.ra.itf.KakaoRAO;
import java.util.List;

public class KakaoRAOImpl implements KakaoRAO {

    private KakaoRAO mapper;

    public void setMapper(KakaoRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public void registerKakao(KakaoDTO kakaoDTO) {
        mapper.registerKakao(kakaoDTO);
    }

    @Override
    public KakaoDTO findOneKakao(String kakaoId) {
        return mapper.findOneKakao(kakaoId);
    }

    @Override
    public void refreshKakao(KakaoDTO kakaoDTO) {
        mapper.refreshKakao(kakaoDTO);
    }

    @Override
    public List<KakaoDTO> findKakaoAll() {
        return mapper.findKakaoAll();
    }

    @Override
    public KakaoDTO findOneKakaoById(int Id) {
        return mapper.findOneKakaoById(Id);
    }

    @Override
    public void deleteKakaoByUserId(String userId) {
        mapper.deleteKakaoByUserId(userId);
    }


}
