package com.soso_server.ra.itf;

import com.soso_server.dto.KakaoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KakaoRAO {
    public void registerKakao(KakaoDTO kakaoDTO);

    public KakaoDTO findOneKakao(@Param("kakaoId") String kakaoId);

    public void refreshKakao(KakaoDTO kakaoDTO);

    public List<KakaoDTO> findKakaoAll();

    public KakaoDTO findOneKakaoById(int Id);

    public void deleteKakaoByUserId(String userId);

}
