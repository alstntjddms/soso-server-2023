package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.service.itf.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/letter")
    public ResponseEntity<List<LetterDTO>> findAllLetter(){
        try {
            return new ResponseEntity<>(letterService.findAllLetter(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
<<<<<<< HEAD
     * letterId로 한개의 Letter를 조회한다.
     * @param userId
     * @return LetterDTO
     */
    @GetMapping("/letter/userId/{userId}")
    public ResponseEntity<List<LetterDTO>> findLetterByUserId(@PathVariable int userId){
        try {
            return new ResponseEntity<>(letterService.selectLetterByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 한개의 Letter를 조회한다.
=======
     * letterId로 한개의 Letter를 조회한다.!!
>>>>>>> main
     * @param letterId
     * @return LetterDTO
     */
    @GetMapping("/letter/{letterId}")
    public ResponseEntity<LetterDTO> findLetter(@PathVariable int letterId){
        try {
            return new ResponseEntity<>(letterService.selectLetter(letterId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Letter를 등록한다.
     * @param letterDTO
     * @param stickerDTO
     * @return 등록된 letterId
     */
    @PostMapping("/letter")
    public ResponseEntity<Integer> registerLetter(@RequestBody LetterDTO letterDTO){
        System.out.println("letterDTO.getLetterId() = " + letterDTO.getLetterId());
        System.out.println("letterDTO.getLetterContent() = " + letterDTO.getLetterContent());
        try{
            return new ResponseEntity<>(letterService.registerLetter(letterDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

}
