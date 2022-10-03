package com.soso_server.ra;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.ra.itf.LetterRAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LetterRAOImpl implements LetterRAO {

    private LetterRAO mapper;

    public void setMapper(LetterRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LetterDTO> findAllLetter() {
        return mapper.findAllLetter();
    }

    @Override
    public int registerLetter(LetterDTO letterDTO) {
        return mapper.registerLetter(letterDTO);
    }

    @Override
    public int registerSticker(StickerDTO stickerDTO) {
//        return mapper.registerSticker(stickerDTO);
        return 1;
    }
    @Override
    public LetterDTO selectLetter(int letterId){
        return mapper.selectLetter(letterId);
    }
    @Override
    public List<LetterDTO> selectLetterByUserId(int userId){
        return mapper.selectLetterByUserId(userId);
    }

}
