package com.soso_server.controller;

import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
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
    @GetMapping("/member/{userId}")
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
     * @param userId
     * @throws Exception
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
     * 멤버의 행성을 개설한다.
     * @param userId
     * @return null
     */
    @PostMapping("/member/opendate")
    public ResponseEntity<Timestamp> registerOpenDate(@RequestBody String userId){
        try{
            System.out.println("MemberController.registerOpenDate");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.registerOpenDate(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 멤버의 행성 개성일을 가져온다.
     * @param userId
     * @return String
     */
    @GetMapping("/member/opendate/{userId}")
    public ResponseEntity<Timestamp> findOpenDate(@PathVariable String userId){
        try{
            System.out.println("MemberController.findOpenDate");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.findOpenDate(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 암호화된 userId를 외부공개용 userId로 다시 암호화한다.
     * @param userId
     * @return String
     * @Throws Exception
     */
    @GetMapping("/member/userid/{userId}")
    public ResponseEntity<String> changeExternalUserId(@PathVariable String userId){
        try{
            System.out.println("MemberController.changeExternalUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.changeExternalUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 외부공개용 userId를 다시 복호화해서 닉네임, 오픈데이트 받아온다.
     * @param userId
     * @return MemberDTO
     * @Throws Exception
     */
    @GetMapping("/member/userid/{userId}")
    public ResponseEntity<MemberDTO> infoByExternalUserId(@PathVariable String userId){
        try{
            System.out.println("MemberController.decryptExternalUserId");
            System.out.println("userId = " + userId);
            return new ResponseEntity<>(memberService.infoByExternalUserId(userId), HttpStatus.OK);
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
            System.out.println("MemberController.findMemberAll");
            return new ResponseEntity<>(memberService.findMemberAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(-999, HttpStatus.BAD_REQUEST);
        }
    }

}
