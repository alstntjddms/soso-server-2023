package com.soso_server.service;

import com.soso_server.AES.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.ra.itf.KakaoRAO;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.service.itf.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    MemberRAO rao;
    AES256 aes256 = new AES256();


    public void setRao(MemberRAO rao) {
        this.rao = rao;
    }

    @Override
    public String registerMember(String id) throws Exception {
        int decId = Integer.valueOf(aes256.decrypt(id));

        KakaoDTO kakaoDTO = rao.findKakaoByKakaoById(decId);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserNickName(kakaoDTO.getKakaoNickName());
        memberDTO.setUserGetLetterCount(0);
        memberDTO.setId(kakaoDTO.getId());

        rao.registerMember(memberDTO);

        MemberDTO result = rao.findMemberById(kakaoDTO.getId());
        return aes256.encrypt(String.valueOf(result.getUserId()));
    }

    @Override
    public List<MemberDTO> findMemberAll() {
        return rao.findMemberAll();
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) throws Exception {
        return rao.findMemberByUserId(Integer.valueOf(aes256.decrypt(userId)));
    }
}
