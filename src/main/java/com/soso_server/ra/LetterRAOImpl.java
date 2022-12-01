package com.soso_server.ra;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.ra.itf.LetterRAO;

import java.util.List;

public class LetterRAOImpl implements LetterRAO {

    private LetterRAO mapper;

    public void setMapper(LetterRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LetterDTO> findLetterAll() {
        return mapper.findLetterAll();
    }

    @Override
    public int registerLetter(LetterDTO letterDTO) {
        return mapper.registerLetter(letterDTO);
    }

    @Override
    public int registerSticker(StickerDTO stickerDTO) {
        return mapper.registerSticker(stickerDTO);
    }
    @Override
    public List<LetterDTO> selectLetterByUserId(int userId){
        return mapper.selectLetterByUserId(userId);
    }

    @Override
    public LetterDTO selectLetter(int letterId) {
        return mapper.selectLetter(letterId);
    }


    @Override
    public List<StickerDTO> selectStickerByLetterId(int letterId) {
        return mapper.selectStickerByLetterId(letterId);
    }

    @Override
    public int selectMaxLetterId() {
        return mapper.selectMaxLetterId();
    }

    @Override
    public void updateUserGetLetterCount(int userId) {
        mapper.updateUserGetLetterCount(userId);
    }

}
