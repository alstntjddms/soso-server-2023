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
    public int registerLetter(HashMap<String, Object> dto) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            LetterDTO letterDTO = mapper.convertValue(dto.get("letter"),LetterDTO.class);
            StickerDTO stickerDTO = mapper.convertValue(dto.get("sticker"),StickerDTO.class);

            rao.registerLetter(letterDTO);

            int maxLetterId = rao.selectMaxLetterId();
            stickerDTO.setLetterId(maxLetterId);
            rao.registerSticker(stickerDTO);


            return maxLetterId;
        }catch (Exception e){
            new LetterException("알수없는 편지 등록오류", -999);
        }
        return 1;
    }

    @Override
    public List<LetterDTO> selectLetterByUserId(String userId) throws Exception {
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }
            int decUserId = Integer.valueOf(aes256.decrypt(userId));
            return rao.selectLetterByUserId(decUserId);
        }catch (MemberException me){
            new MemberException("잘못된 userId", -999);
        }catch (Exception e) {
            new LetterException("잘못된 편지 조회 요청", -999);
        }
        return null;
    }

    @Override
    public List<StickerDTO> findStickerByLetterId(int letterId) {
        return rao.selectStickerByLetterId(letterId);
    }
}
