package com.soso_server.controller;

import com.soso_server.dto.MemberDTO;
import com.soso_server.service.itf.MemberService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

@RestController
public class MemberController {

    private static final Logger logger = Logger.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    /**
     * 암호화된 kakao테이블 id로 정보를 찾아 멤버에 등록한다.
     * @param id
     * @return 암호화된 member의 userId
     * @throws Exception
     */
    @PostMapping("/member")
    public synchronized ResponseEntity<String> registerMember(@RequestBody String id){
        try {
            logger.info("[registerMember] MemberController.registerMember");
            logger.info("[registerMember] id = " + id);
            return new ResponseEntity<String>(memberService.registerMember(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 Member를 찾는다.
     * @param userId
     * @return MemberDTO
     * @throws Exception
     */
    @GetMapping("/member/{userId}")
    public ResponseEntity<?> findMemberByUserId(@PathVariable String userId){
        try {
            logger.info("[findMemberByUserId] MemberController.findMemberByUserId");
            logger.info("[findMemberByUserId] userId = " + userId);
            return new ResponseEntity<MemberDTO>(memberService.findMemberByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * userId로 userNickName 변경한다.
     * @param userId
     * @return MemberDTO
     * @throws Exception
     */
    @PatchMapping(value = "/member/{userId}")
    public ResponseEntity<String> modifyUserNickNameByUserId(@PathVariable String userId, @RequestBody String userNickName){
        try {
            logger.info("[modifyUserNickNameByUserId] MemberController.modifyUserNickNameByUserId");
            logger.info("[modifyUserNickNameByUserId] userId = " + userId);
            logger.info("[modifyUserNickNameByUserId] userNickName = " + userNickName);
            return new ResponseEntity<String>(memberService.modifyUserNickNameByUserId(userId, userNickName), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 내부용
     * userId로 받은 편지의 개수를 조회한다.
     * @param userId
     * @throws Exception
     */
    @GetMapping("/member/lettercount/{userId}")
    public ResponseEntity<String> findLetterCountByUserId(@PathVariable String userId){
        try{
            logger.info("[findMemberByLetterCount] MemberController.findMemberByLetterCount");
            logger.info("[findMemberByLetterCount] userId = " + userId);
            return new ResponseEntity<String>(memberService.findMemberByLetterCount(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 외부용
     * userId로 받은 편지의 개수를 조회한다.
     * @param userId
     * @throws Exception
     */
    @GetMapping("/member/external/lettercount/{userId}")
    public ResponseEntity<?> findLetterCountByExternalUserId(@PathVariable String userId){
        try{
            logger.info("[findLetterCountByExternalUserId] MemberController.findLetterCountByExternalUserId");
            logger.info("[findLetterCountByExternalUserId] userId = " + userId);
            return new ResponseEntity<Integer>(memberService.findLetterCountByExternalUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 멤버의 행성을 개설한다.
     * @param userId
     * @return null
     */
    @PostMapping("/member/opendate")
    public ResponseEntity<?> registerOpenDate(@RequestBody String userId){
        try{
            logger.info("[registerOpenDate] MemberController.registerOpenDate");
            logger.info("[registerOpenDate] userId = " + userId);
            return new ResponseEntity<Timestamp>(memberService.registerOpenDate(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 멤버의 행성을 개설한다.
     * @param userId
     * @return null
     */
    @PatchMapping("/member/refresh/opendate")
    public ResponseEntity<?> refreshOpenDate(@RequestBody String userId){
        try{
            logger.info("[refreshOpenDate] MemberController.refreshOpenDate");
            logger.info("[refreshOpenDate] userId = " + userId);
            return new ResponseEntity<Timestamp>(memberService.refreshOpenDate(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 멤버의 행성 개성일을 가져온다.
     * @param userId
     * @return String
     */
    @GetMapping("/member/opendate/{userId}")
    public ResponseEntity<?> findOpenDate(@PathVariable String userId){
        try{
            logger.info("[findOpenDate] MemberController.findOpenDate");
            logger.info("[findOpenDate] userId = " + userId);
            return new ResponseEntity<Timestamp>(memberService.findOpenDate(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
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
            logger.info("[changeExternalUserId] MemberController.changeExternalUserId");
            logger.info("[changeExternalUserId] userId = " + userId);
            return new ResponseEntity<String>(memberService.changeExternalUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 외부공개용 userId를 다시 복호화해서 닉네임, 오픈데이트 받아온다.
     * @param userId
     * @return MemberDTO
     * @Throws Exception
     */
    @GetMapping("/member/external/userid/{userId}")
    public ResponseEntity<?> infoByExternalUserId(@PathVariable String userId){
        try{
            logger.info("[decryptExternalUserId] MemberController.decryptExternalUserId");
            logger.info("[decryptExternalUserId] userId = " + userId);
            return new ResponseEntity<MemberDTO>(memberService.infoByExternalUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("-999", HttpStatus.BAD_REQUEST);
        }
    }

}
