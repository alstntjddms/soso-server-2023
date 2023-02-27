package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.service.itf.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LetterController {

    @Autowired
    LetterService letterService;

    /**
     * !!!!!관리용!!!!!
     *
     * 전체 Letter를 조회한다.
     * @return List<LetterDTO>
     */
    @GetMapping("/letterall")
    public ResponseEntity<List<LetterDTO>> findAllLetter(){
        try {
            System.out.println("LetterController.findAllLetter");
            return new ResponseEntity<>(letterService.findLetterAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 받은 전체 LetterId를 조회한다.
     * @param userId
     * @return LetterDTO
     */
    @GetMapping("/letter/userid/{userId}")
    public ResponseEntity<List<LetterDTO>> findLetterByUserId(@PathVariable String userId){
        try {
            System.out.println("LetterController.findLetterByUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(letterService.selectLetterIdByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Letter를 등록한다.
     * @param HashMap<String, Object> dto
     * @return 등록된 letterId
     */
    @PostMapping("/letter")
    public ResponseEntity<String> registerLetter(@RequestBody HashMap<String, Object> dto){
        try{
            System.out.println("LetterController.registerLetter");
            System.out.println("dto = " + dto);
            return new ResponseEntity<>(letterService.registerLetter(dto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 받은 한개 Letter를 조회한다.
     * @param letterId
     * @return LetterDTO
     */
    @GetMapping("/letter/{letterId}")
    public ResponseEntity<LetterDTO> findLetterByLetterId(@PathVariable String letterId){
        try {
            System.out.println("LetterController.findLetterByLetterId");
            System.out.println("letterId = " + letterId);
            return new ResponseEntity<>(letterService.selectLetterByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 스티커를 조회한다.
     * @param letterId
     * @return List<StickerDTO>
     */
    @GetMapping("/sticker/letterid/{letterId}")
    public ResponseEntity<StickerDTO> findStickerByLetterId(@PathVariable String letterId){
        try{
            System.out.println("LetterController.findStickerByLetterId");
            System.out.println("letterId = " + letterId);
            return new ResponseEntity(letterService.findStickerByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }
}
