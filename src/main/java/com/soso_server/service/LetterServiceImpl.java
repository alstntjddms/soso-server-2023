package com.soso_server.service;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.LetterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    LetterRAO rao;

    public void setRao(LetterRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<LetterDTO> findAllLetter() {
        try{
            List<LetterDTO> letterDTOS = rao.findAllLetter();
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
    public int registerLetter(LetterDTO letterDTO) {
        try{
            // 저장 시간 설정
            letterDTO.setLetterWriteDate(new Timestamp(System.currentTimeMillis()));

            // 등록 전 letterId 체크
            int letterId = letterDTO.getLetterId();
            if(rao.selectLetter(letterId) != null){
                new LetterException("중복된 편지지 아이디", 999);
            }

//            rao.registerSticker(stickerDTO);
            System.out.println("letterDTO = ");
            System.out.println(letterDTO);
            return rao.registerLetter(letterDTO);

        }catch (Exception e){
            new LetterException("알수없는 편지 등록오류", -999);
        }
        return 1;
    }

    @Override
    public LetterDTO selectLetter(int letterId) {
        return rao.selectLetter(letterId);
    }

    @Override
    public List<LetterDTO> selectLetterByUserId(int userId) {
        return rao.selectLetterByUserId(userId);
    }
}
