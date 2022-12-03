package com.soso_server.service;

import com.soso_server.utils.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.exception.MemberException;
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
    public String registerMember(String id){
        try{
            if(id.length() < 20){
                throw new MemberException();
            }
            int decId = Integer.valueOf(aes256.decrypt(id));

            KakaoDTO kakaoDTO = rao.findKakaoByKakaoById(decId);

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserNickName(kakaoDTO.getKakaoNickName());
            memberDTO.setUserGetLetterCount(0);
            memberDTO.setId(kakaoDTO.getId());

            rao.registerMember(memberDTO);

            return aes256.encrypt(String.valueOf(rao.findMemberById(kakaoDTO.getId()).getUserId()));
        }catch (MemberException me){
            new MemberException("잘못된 id", -999);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MemberDTO findMemberByUserId(String userId){
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            return rao.findMemberByUserId(Integer.valueOf(aes256.decrypt(userId)));
        }catch (MemberException me){
            new MemberException("잘못된 userId", -999);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MemberDTO> findMemberAll() {
        return rao.findMemberAll();
    }

}
