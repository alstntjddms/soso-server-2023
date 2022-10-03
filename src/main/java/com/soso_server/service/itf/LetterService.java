package com.soso_server.service.itf;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;

import java.util.List;

public interface LetterService {
    /**
     * 모든 편지를 조회한다.
     * param void
     * return List<LetterDTO>
     */
    public List<LetterDTO> findAllLetter() throws LetterException;

    /**
     * 편지를 등록한다.
     * param LetterDTO
     * return letterId
     */
    public int registerLetter(LetterDTO letterDTO );

    /**
     * letterId로 LetterDTO를 조회한다.
     */
    public LetterDTO selectLetter(int letterId);

    /**
     * userId로 LetterDTO를 조회한다.
     */
    public List<LetterDTO> selectLetterByUserId(int userId);

}
