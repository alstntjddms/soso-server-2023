package com.soso_server.controller;

import com.soso_server.service.itf.LetterService;
import com.soso_server.service.itf.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    @Autowired
    MemberService memberService;
}
