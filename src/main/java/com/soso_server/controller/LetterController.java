package com.soso_server.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.service.itf.LetterService;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LetterController {

    @Autowired
    LetterService letterService;

    /**
     * 전체 Letter를 조회한다.
     * @return List<LetterDTO>
     */
    @GetMapping("/letterall")
    public ResponseEntity<List<LetterDTO>> findAllLetter(){
        try {
            return new ResponseEntity<>(letterService.findLetterAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 한개의 Letter를 조회한다.
     * @param userId
     * @return LetterDTO
     */
    @GetMapping("/letter/userid/{userId}")
    public ResponseEntity<List<LetterDTO>> findLetterByUserId(@PathVariable String userId){
        try {
            return new ResponseEntity<>(letterService.selectLetterByUserId(userId), HttpStatus.OK);
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
    public ResponseEntity<Integer> registerLetter(@RequestBody HashMap<String, Object> dto){
        try{
            return new ResponseEntity<>(letterService.registerLetter(dto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * letterId로 스티커를 조회한다.
     * @param letterId
     * @return List<StickerDTO>
     */
    @GetMapping("/sticker/{letterId}")
    public ResponseEntity<List<StickerDTO>> findStickerByLetterId(@PathVariable int letterId){
        try{
            return new ResponseEntity(letterService.findStickerByLetterId(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }
}
