package com.soso_server.controller;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.service.itf.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LetterController {

    @Autowired
    LetterService service;

    @GetMapping("/letter")
    public List<LetterDTO> findAllLetter(){
        return service.findAllLetter();
    }

    @PostMapping("/letter")
    public int registerLetter(LetterDTO letterDTO, StickerDTO stickerDTO) {
        return service.registerLetter(letterDTO, stickerDTO);
    }


}
