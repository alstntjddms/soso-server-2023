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

    /**
     * 암호화된 kakao테이블 id로 정보를 찾아 멤버에 등록한다.
     * @param id
     * @return 암호화된 member의 userId
     * @throws Exception
     */
    @PostMapping("/member")
    public String registerMember(@RequestBody String id) throws Exception {
        return memberService.registerMember(id);
    }

    /**
     * userId로 Member를 찾는다.
     * @param userId
     * @return MemberDTO
     * @throws Exception
     */
    @GetMapping("/memberbyuserid/{userId}")
    public MemberDTO findMemberByUserId(@PathVariable String userId) throws Exception {
        return memberService.findMemberByUserId(userId);
    }

    /**
     * 모든 멤버를 조회한다.
     * @return List<MemberDTO>
     */
    @GetMapping("/memberall")
    public List<MemberDTO> findMemberAll(){
        return memberService.findMemberAll();
    }

}
