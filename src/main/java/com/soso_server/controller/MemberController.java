package com.soso_server.controller;

import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    @Autowired
    MemberService memberService;

    //member를 Kakao테이블의 id를 찾아 등록한다.
    @PostMapping("/member")
    public String registerMember(@RequestBody String id) throws Exception {
        return memberService.registerMember(id);
    }

    //userId로 Member를 찾는다.
    @GetMapping("/memberbyuserid/{userId}")
    public MemberDTO findMemberByUserId(@PathVariable String userId) throws Exception {
        return memberService.findMemberByUserId(userId);
    }

    //모든 member를 찾는다.
    @GetMapping("/memberall")
    public List<MemberDTO> findMemberAll(){
        return memberService.findMemberAll();
    }

}
