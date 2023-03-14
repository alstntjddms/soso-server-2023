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
        mapper.registerLetter(letterDTO);
        return Integer.valueOf(letterDTO.getLetterId());
    }

    @Override
    public int registerSticker(StickerDTO stickerDTO) {
        return mapper.registerSticker(stickerDTO);
    }

    @Override
    public List<LetterDTO> selectLetterIdByUserId(int userId) {
        return mapper.selectLetterIdByUserId(userId);
    }

    @Override
    public LetterDTO selectLetter(int letterId) {
        return mapper.selectLetter(letterId);
    }

    @Override
    public LetterDTO selectLetterByLetterId(int letterId) {
        return mapper.selectLetterByLetterId(letterId);
    }


    @Override
    public List<StickerDTO> selectStickerByLetterId(int letterId) {
        return mapper.selectStickerByLetterId(letterId);
    }

    @Override
    public void updateToReadLetter(Integer letterId) {
        mapper.updateToReadLetter(letterId);
    }

}
