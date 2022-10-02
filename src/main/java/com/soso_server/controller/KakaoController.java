package com.soso_server.controller;

import com.soso_server.service.itf.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoController {

    @Autowired
    KakaoService service;

    @GetMapping("/kakao")
    public KakaoService getService() {
        return service;
    }
}
