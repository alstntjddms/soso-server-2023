package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.service.itf.LetterService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LetterController {

    private static final Logger logger = Logger.getLogger(LetterController.class);

    @Autowired
    LetterService letterService;

    /**
     * userId로 받은 전체 LetterId를 조회한다.
     * @param userId
     * @return LetterDTO
     */
    @GetMapping("/letter/userid/{userId}")
    public ResponseEntity<?> findLetterByUserId(@PathVariable String userId){
        try {
            logger.debug("[findLetterByUserId] LetterController.findLetterByUserId");
            logger.debug("[findLetterByUserId] userId = " + userId);
            return new ResponseEntity<List<LetterDTO>>(letterService.selectLetterIdByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Letter를 등록한다.
     * @param HashMap<String, Object> dto
     * @return 등록된 letterId
     */
    @PostMapping("/letter")
    public synchronized ResponseEntity<String> registerLetter(@RequestBody HashMap<String, Object> dto){
        try{
            logger.debug("[registerLetter] LetterController.registerLetter");
            logger.debug("[registerLetter] dto = " + dto);
            return new ResponseEntity<String>(letterService.registerLetter(dto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 받은 한개 Letter를 조회한다.
     * @param letterId
     * @return LetterDTO
     */
    @GetMapping("/letter/{letterId}")
    public ResponseEntity<?> findLetterByLetterId(@PathVariable String letterId){
        try {
            logger.debug("[findLetterByLetterId] LetterController.findLetterByLetterId");
            logger.debug("[findLetterByLetterId] letterId = " + letterId);
            return new ResponseEntity<LetterDTO>(letterService.selectLetterByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 스티커를 조회한다.
     * @param letterId
     * @return List<StickerDTO>
     */
    @GetMapping("/sticker/letterid/{letterId}")
    public ResponseEntity<?> findStickerByLetterId(@PathVariable String letterId){
        try{
            logger.debug("[findStickerByLetterId] LetterController.findStickerByLetterId");
            logger.debug("[findStickerByLetterId] letterId = " + letterId);
            return new ResponseEntity<List<StickerDTO>>(letterService.findStickerByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 letter를 차단한다.
     * @param letterId
     * @return letterId
     */
    @GetMapping("/letter/block/{letterId}")
    public ResponseEntity<String> blockByLetterId(@PathVariable String letterId){
        try{
            logger.debug("[blockByLetterId] LetterController.blockByLetterId");
            logger.debug("[blockByLetterId] letterId = " + letterId);
            return new ResponseEntity<String>(letterService.blockByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }
}
