package com.soso_server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soso_server.utils.AES256;
import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;
import com.soso_server.exception.MemberException;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.LetterService;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    LetterRAO rao;
    AES256 aes256 = new AES256();


    public void setRao(LetterRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<LetterDTO> findLetterAll() {
        try{
            List<LetterDTO> letterDTOS = rao.findLetterAll();
            if(letterDTOS.size() > 0){
             return letterDTOS;
            }else{
                new LetterException("데이터 없음", 999);
            }
        }catch (Exception e){
            new LetterException("알수없는 편지조회 오류", -999);
        }
        return null;
    }

    @Override
    public String registerLetter(HashMap<String, Object> dto) {
        try{
            System.out.println("LetterServiceImpl.registerLetter");
            ObjectMapper mapper = new ObjectMapper();
            HashMap tmpLetterDTO = mapper.convertValue(dto.get("letter"), HashMap.class);
            tmpLetterDTO.replace("userId", aes256.decrypt((String)tmpLetterDTO.get("userId")));

            LetterDTO letterDTO = mapper.convertValue(dto.get("letter"), LetterDTO.class);
            System.out.println("letterDTO = " + letterDTO);

            letterDTO.setUserId(Integer.parseInt(URLDecoder.decode(aes256.decrypt(String.valueOf(letterDTO.getUserId())), "UTF-8" )));
            StickerDTO stickerDTO = mapper.convertValue(dto.get("sticker"), StickerDTO.class);

            System.out.println("letterDTO = " + letterDTO);
            System.out.println("stickerDTO = " + stickerDTO);

            rao.registerLetter(letterDTO);

            int maxLetterId = rao.selectMaxLetterId();
            stickerDTO.setLetterId(maxLetterId);
            rao.registerSticker(stickerDTO);

            return aes256.encrypt(String.valueOf(maxLetterId));
        }catch (Exception e){
            new LetterException("알수없는 편지 등록오류", -999);
        }
        return "";
    }

    @Override
    public List<LetterDTO> selectLetterIdByUserId(String userId){
        try{
            System.out.println("LetterServiceImpl.selectLetterIdByUserId");
            if(userId.length() < 20){
                throw new MemberException();
            }
            int decryptUserId = Integer.valueOf(aes256.decrypt(userId));

            System.out.println("LetterServiceImpl.selectLetterIdByUserId");

            List<LetterDTO> result = null;
            for(LetterDTO letterDTO : rao.selectLetterIdByUserId(decryptUserId)){
                letterDTO.setLetterId(Integer.parseInt(URLEncoder.encode(aes256.encrypt(String.valueOf(letterDTO.getLetterId())), "UTF-8")));
                letterDTO.setUserId(Integer.parseInt(aes256.encrypt(String.valueOf(letterDTO.getUserId()))));
                result.add(letterDTO);
            }
            System.out.println("LetterServiceImpl.selectLetterIdByUserId");
            return result;
        }catch (MemberException me){
            new MemberException("잘못된 userId", -999);
        }catch (Exception e) {
            new LetterException("잘못된 편지 조회 요청", -999);
        }
        return null;
    }

    @Override
    public LetterDTO selectLetterByLetterId(String letterId) {
        try {
            if(letterId.length() < 20){
                throw new LetterException();
            }
            return rao.selectLetterByLetterId(Integer.valueOf(aes256.decrypt(letterId)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StickerDTO findStickerByLetterId(int letterId) {
        return rao.selectStickerByLetterId(letterId);
    }
}
