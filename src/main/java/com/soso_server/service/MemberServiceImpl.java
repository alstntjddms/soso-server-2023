package com.soso_server.service;

import com.soso_server.utils.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.exception.MemberException;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.service.itf.MemberService;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.sql.Timestamp;
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
            System.out.println("MemberServiceImpl.registerMember");
            if(id.length() < 20){
                throw new MemberException();
            }
            int decId = Integer.valueOf(aes256.decrypt(id));

            KakaoDTO kakaoDTO = rao.findKakaoByKakaoById(decId);

            if(rao.findMemberById(kakaoDTO.getId()) == null){
                System.out.println("신규 아이디 등록");
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setUserNickName(kakaoDTO.getKakaoNickName());
                memberDTO.setId(kakaoDTO.getId());
                rao.registerMember(memberDTO);
            }else{
                System.out.println("기존 아이디 있음");
                return URLEncoder.encode(aes256.encrypt(String.valueOf(rao.findMemberById(kakaoDTO.getId()).getUserId())), "UTF-8");
            }
            return URLEncoder.encode(aes256.encrypt(String.valueOf(rao.findMemberById(kakaoDTO.getId()).getUserId())), "UTF-8");
        }catch (MemberException me){
            new MemberException("잘못된 id", -999);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) throws MemberException {
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            MemberDTO memberDTO = rao.findMemberByUserId(Integer.parseInt(aes256.decrypt(userId)));
            memberDTO.setId(0);
            memberDTO.setUserId(memberDTO.getUserId());
            return memberDTO;
        }catch (MemberException me){
            throw new MemberException("잘못된 userId", -999);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findMemberByLetterCount(String userId) throws MemberException {
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            return rao.findMemberByLetterCount(Integer.parseInt(aes256.decrypt(userId)));
        }catch (MemberException me){
            throw new MemberException("잘못된 userId", -999);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Timestamp registerOpenDate(String userId){
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            return rao.registerOpenDate(Integer.parseInt(aes256.decrypt(userId)));
        }catch(MemberException e){
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Timestamp findOpenDate(String userId) {
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            return rao.findOpenDate(Integer.parseInt(aes256.decrypt(userId)));
        }catch(MemberException e){
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MemberDTO> findMemberAll() {
        return rao.findMemberAll();
    }

}
