package com.soso_server.service.itf;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface LetterService {
    /**
     * 모든 편지를 조회한다.
     * param void
     * @return List<LetterDTO>
     */
    public List<LetterDTO> findLetterAll() throws LetterException;

    /**
     * 편지를 등록한다.
     * @param letterDTO, stickerDTO
     * @param dto
     * @return letterId
     */
    public int registerLetter(HashMap<String, Object> dto);


    /**
     * userId로 LetterDTO를 조회한다.
     * @param userId
     * @return List<LetterDTO>
     */
    public List<LetterDTO> selectLetterByUserId(String userId) throws Exception;

    public List<StickerDTO> findStickerByLetterId(int letterId);

}
