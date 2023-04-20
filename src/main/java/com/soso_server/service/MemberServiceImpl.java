package com.soso_server.service;

import com.soso_server.utils.AES256;
import com.soso_server.dto.KakaoDTO;
import com.soso_server.dto.MemberDTO;
import com.soso_server.exception.MemberException;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.service.itf.MemberService;
import com.soso_server.utils.ExternalAES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = Logger.getLogger(MemberServiceImpl.class);

    MemberRAO rao;

    @Autowired
    AES256 aes256;

    @Autowired
    ExternalAES256 externalAES256;

    public void setRao(MemberRAO rao) {
        this.rao = rao;
    }

    @Override
    @Transactional
    public synchronized String registerMember(String id){
        try{
            logger.info("[registerMember] Start");
            if(id.length() < 20){
                throw new MemberException();
            }
            int decId = Integer.valueOf(aes256.decrypt(id));

            KakaoDTO kakaoDTO = rao.findKakaoByKakaoById(decId);

            if(rao.findMemberById(kakaoDTO.getId()) == null){
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setUserNickName(kakaoDTO.getKakaoNickName());
                memberDTO.setId(kakaoDTO.getId());
                logger.info("[registerMember] 신규 멤버 생성");
                rao.registerMember(memberDTO);
            }
            logger.info("[registerMember] End");
            return aes256.encryptEncodeReplace(String.valueOf(rao.findMemberById(kakaoDTO.getId()).getUserId()));
        }catch (MemberException me){
            logger.info("[registerMember] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[registerMember] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) throws MemberException {
        try{
            logger.info("[findMemberByUserId] Start");

            logger.info("[findMemberByUserId] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }
            MemberDTO memberDTO = rao.findMemberByUserId(Integer.parseInt(aes256.replaceDecodeDecryt(userId)));

            memberDTO.setId(0);
            memberDTO.setUserId(memberDTO.getUserId());

            // 10일뒤로 변경해서 리턴
            if(memberDTO.getUserOpenDate() != null){
                long newTimestampInMillis = memberDTO.getUserOpenDate().getTime() + 864000000L;
                Timestamp newTimestamp = new Timestamp(newTimestampInMillis);
                memberDTO.setUserOpenDate(newTimestamp);
            }

            logger.info("[findMemberByUserId] Start");
            return memberDTO;
        }catch (MemberException me){
            logger.info("[findMemberByUserId] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[findMemberByUserId] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public String modifyUserNickNameByUserId(String userId, String userNickName) throws MemberException {
        try{
            logger.info("[modifyUserNickNameByUserId] Start");

            logger.info("[modifyUserNickNameByUserId] userId = " + userId + "userNickName = " + userNickName);

            if(userNickName.length() > 30){
                throw new MemberException();
            }
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(aes256.replaceDecodeDecryt(userId));
            memberDTO.setUserNickName(userNickName);
            rao.modifyUserNickNameByUserId(memberDTO);

            logger.info("[modifyUserNickNameByUserId] End");
            return userNickName;
        }catch (Exception e){
            logger.info("[modifyUserNickNameByUserId] Exception = " + e.getMessage());
        }
        return "";
    }

    @Override
    public String findMemberByLetterCount(String userId) throws MemberException {
        try{
            logger.info("[findMemberByLetterCount] Start");

            logger.info("[findMemberByLetterCount] userId = " + userId);

            if(userId.length() < 20){
                throw new MemberException();
            }

            logger.info("[findMemberByLetterCount] End");
            return String.valueOf(rao.findMemberByLetterCount(Integer.parseInt(aes256.replaceDecodeDecryt(userId))));
        }catch (MemberException me){
            logger.info("[findMemberByLetterCount] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[findMemberByLetterCount] Exception = " + e.getMessage());
        }
        return "";
    }

    @Override
    public Timestamp registerOpenDate(String userId) throws MemberException {
        try{
            logger.info("[registerOpenDate] Start");

            logger.info("[registerOpenDate] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }
            Timestamp openDate = findOpenDate(userId);
            // 864000 = 1일
            if(openDate != null && (new Timestamp(System.currentTimeMillis()-8640000)).before(openDate)){
                logger.info("[refreshOpenDate] 이미 오픈데이트가 설정됨");
                throw new MemberException("이미 오픈데이트가 설정됨.", -999);
            }

            logger.info("[registerOpenDate] End");
            return rao.registerOpenDate(Integer.parseInt(aes256.replaceDecodeDecryt(userId))); 
        }catch(MemberException me){
            logger.info("[registerOpenDate] MemberException = " + me.getMessage());
        } catch (Exception e) {
            logger.info("[registerOpenDate] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public Timestamp refreshOpenDate(String userId) throws MemberException {
        try{
            logger.info("[refreshOpenDate] Start");

            logger.info("[refreshOpenDate] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }

            logger.info("[refreshOpenDate] End");
            return rao.refreshOpenDate(Integer.parseInt(aes256.replaceDecodeDecryt(userId)));
        }catch(MemberException me){
            logger.info("[refreshOpenDate] MemberException = " + me.getMessage());
        } catch (Exception e) {
            logger.info("[refreshOpenDate] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public Timestamp findOpenDate(String userId) throws MemberException {
        try{
            logger.info("[findOpenDate] Start");

            logger.info("[findOpenDate] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }

            logger.info("[findOpenDate] End");
            return rao.findOpenDate(Integer.parseInt(aes256.replaceDecodeDecryt(userId)));
        }catch(MemberException me){
            logger.info("[findOpenDate] MemberException = " + me.getMessage());
        } catch (Exception e) {
            logger.info("[findOpenDate] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public String changeExternalUserId(String userId) throws Exception {
        try {
            logger.info("[changeExternalUserId] Start");

            logger.info("[changeExternalUserId] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }

            logger.info("[changeExternalUserId] End");
            return externalAES256.encryptEncodeReplace(aes256.replaceDecodeDecryt(userId));
        }catch (MemberException me){
            logger.info("[changeExternalUserId] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[changeExternalUserId] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public MemberDTO infoByExternalUserId(String userId) throws MemberException {
        try {
            logger.info("[infoByExternalUserId] Start");

            logger.info("[infoByExternalUserId] userId = " + userId);
            if(userId.length() < 20){
                throw new MemberException();
            }

//            Integer decUserId = Integer.parseInt(externalAES256.decrypt(URLDecoder.decode(userId.replaceAll("MSJSM", "%"), "UTF-8")));
            Integer decUserId = Integer.parseInt(externalAES256.replaceDecodeDecryt(userId));

            MemberDTO memberDTO = rao.findMemberByUserId(decUserId);
            memberDTO.setId(0);
            memberDTO.setUserId("");
            memberDTO.setUserDate(null);
            // 10일뒤로 변경해서 리턴
            long newTimestampInMillis = memberDTO.getUserOpenDate().getTime() + 864000000L;
            Timestamp newTimestamp = new Timestamp(newTimestampInMillis);
            memberDTO.setUserOpenDate(newTimestamp);

            logger.info("[infoByExternalUserId] End");
            return memberDTO;
        }catch (MemberException me){
            logger.info("[infoByExternalUserId] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[infoByExternalUserId] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer findLetterCountByExternalUserId(String userId) {
        try{
            logger.info("[findLetterCountByExternalUserId] Start");

            logger.info("[findLetterCountByExternalUserId] userId = " + userId);

            if(userId.length() < 20){
                throw new MemberException();
            }

            logger.info("[findLetterCountByExternalUserId] End");
            return rao.findMemberByLetterCount(Integer.parseInt(externalAES256.replaceDecodeDecryt(userId)));
        }catch (MemberException me){
            logger.info("[findLetterCountByExternalUserId] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[findLetterCountByExternalUserId] Exception = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<MemberDTO> findMemberAll() {
        try{
            logger.info("[findMemberAll] Start");

            List<MemberDTO> memberDTOS = rao.findMemberAll();
            if(memberDTOS.size() > 0){
                logger.info("[findLetterAll] End");
                return memberDTOS;
            }else{
                throw new MemberException("데이터 없음", 999);
            }
        }catch (MemberException me){
            logger.info("[findMemberAll] MemberException = " + me.getMessage());
        }catch (Exception e){
            logger.info("[findMemberAll] Exception = " + e.getMessage());
        }
        logger.info("[findMemberAll] End");
        return null;
    }

}