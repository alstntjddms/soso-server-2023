package com.soso_server.service;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.LetterService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LetterServiceImpl implements LetterService {

    LetterRAO rao;

    public void setRao(LetterRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<LetterDTO> findAllLetter() {
        return rao.findAllLetter();
    }

    @Transactional
    @Override
    public int registerLetter(LetterDTO letterDTO, StickerDTO stickerDTO) {
        //스티커 등록
        rao.registerSticker(stickerDTO);
        //편지 등록
        rao.registerLetter(letterDTO);
        return 1;
    }

}
