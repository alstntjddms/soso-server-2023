package com.soso_server.controller;

import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
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
    public ResponseEntity<String> registerMember(@RequestBody String id){
        try {
            System.out.println("MemberController.registerMember");
            System.out.println("id = " + id);
            return new ResponseEntity<>(memberService.registerMember(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 Member를 찾는다.
     * @param userId
     * @return MemberDTO
     * @throws Exception
     */
    @GetMapping("/memberbyuserid/{userId}")
    public ResponseEntity<MemberDTO> findMemberByUserId(@PathVariable String userId){
        try {
            System.out.println("MemberController.findMemberByUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.findMemberByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 받은 편지의 개수를 조회한다.
     */
    @GetMapping("/member/lettercount/{userId}")
    public ResponseEntity<Integer> findMemberByLetterCount(@PathVariable String userId){
        try{
            System.out.println("MemberController.findMemberByLetterCount");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.findMemberByLetterCount(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 멤버를 조회한다.
     * @return List<MemberDTO>
     */
    @GetMapping("/memberall")
    public ResponseEntity<List<MemberDTO>> findMemberAll(){
        try {
            return new ResponseEntity<>(memberService.findMemberAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

}
