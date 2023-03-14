package com.soso_server.ra.itf;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;

import java.util.List;

public interface LetterRAO {

    /**
     * 모든 편지를 조회한다.
     * param void
     * return List<LetterDTO>
     */
    public List<LetterDTO> findLetterAll();

    /**
     * 편지를 등록한다.
     * param LetterDTO
     * return letterId
     */
    public int registerLetter(LetterDTO letterDTO);

    /**
     * 스티커를 등록한다.
     * param LetterDTO
     * return letterId
     */
    public int registerSticker(StickerDTO stickerDTO);


    /**
     * uesrId로 LetterDTO를 조회한다.
     */
    public List<LetterDTO> selectLetterIdByUserId(int userId);

    public LetterDTO selectLetter(int letterId);

    public LetterDTO selectLetterByLetterId(int letterId);

    public List<StickerDTO> selectStickerByLetterId(int letterId);

}
