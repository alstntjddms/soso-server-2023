package com.soso_server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soso_server.ra.itf.MemberRAO;
import com.soso_server.service.itf.MessageService;
import com.soso_server.utils.AES256;
import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;
import com.soso_server.exception.MemberException;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.LetterService;
import com.soso_server.utils.ExternalAES256;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    private static final Logger logger = Logger.getLogger(LetterServiceImpl.class);


    LetterRAO rao;

    @Autowired
    MemberRAO memberRAO;

    @Autowired
    MessageService messageService;

    @Autowired
    AES256 aes256;

    @Autowired
    ExternalAES256 externalAES256;

    public void setRao(LetterRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<LetterDTO> findLetterAll() {
        try{
            logger.info("[findLetterAll] Start");

            List<LetterDTO> letterDTOS = rao.findLetterAll();
            if(letterDTOS.size() > 0){
                logger.info("[findLetterAll] End");
                return letterDTOS;
            }else{
                throw new LetterException("데이터 없음", 999);
            }
        }catch (LetterException le){
            logger.warn("[findLetterAll] LetterException = " + le.getMessage());
        }catch (Exception e){
            logger.warn("[findLetterAll] Exception = " + e.getMessage());
        }
        logger.info("[findLetterAll] End");
        return null;
    }

    @Override
    @Transactional
    public String registerLetter(HashMap<String, Object> dto) {
        try{
            logger.info("[registerLetter] Start");

            ObjectMapper mapper = new ObjectMapper();
            LetterDTO letterDTO = mapper.convertValue(dto.get("letter"), LetterDTO.class);
            String userId = externalAES256.replaceDecodeDecryt(letterDTO.getUserId());
            letterDTO.setUserId(userId);
            letterDTO.setLetterReadYn(false);
            letterDTO.setLetterDelYn(false);
            int registerLetterId = rao.registerLetter(letterDTO);

            ArrayList<StickerDTO> stickerDTOs = mapper.convertValue(dto.get("sticker"), new TypeReference<ArrayList<StickerDTO>>(){});
             for(StickerDTO a : stickerDTOs){
                 a.setLetterId(registerLetterId);
                 rao.registerSticker(a);
             }

            // 편지 개수 알림 함수 호출
            messageService.sendMessageByLetterCount(Integer.parseInt(userId));

            logger.info("[registerLetter] End");
            return externalAES256.encrypt(String.valueOf(registerLetterId));
        }catch(LetterException le){
            logger.warn("[registerLetter] LetterException = " + le.getMessage());
        }catch (Exception e){
            logger.warn("[registerLetter] Exception = " + e.getMessage());
        }
        logger.info("[registerLetter] End");
        return "편지등록 실패";
    }

    @Override
    public List<LetterDTO> selectLetterIdByUserId(String userId){
        try{
            logger.info("[selectLetterIdByUserId] Start");

            logger.info("[selectLetterIdByUserId] userId = " + userId);

            if(userId.length() < 20){
                throw new MemberException();
            }

            List<LetterDTO> result = new ArrayList<>();

            int decUserId = Integer.parseInt(aes256.replaceDecodeDecryt(userId));

            for(LetterDTO letterDTO : rao.selectLetterIdByUserId(decUserId)){
                letterDTO.setLetterId(aes256.encryptEncodeReplace(letterDTO.getLetterId()));
                letterDTO.setUserId("");
                letterDTO.setLetterContent("");
                letterDTO.setLetterFont("");
                letterDTO.setLetterFontColor("");
                letterDTO.setLetterPaper("");
                letterDTO.setLetterFontSize("");
                letterDTO.setLetterTextAlign("");
                letterDTO.setLetterWriter("");
                result.add(letterDTO);
            }

            logger.info("[selectLetterIdByUserId] End");
            return result;
        }catch (MemberException me){
            logger.warn("[selectLetterIdByUserId] MemberException = " + me.getMessage());
        }catch (LetterException le) {
            logger.warn("[selectLetterIdByUserId] LetterException = " + le.getMessage());
        }catch(Exception e){
            logger.warn("[selectLetterIdByUserId] Exception = " + e.getMessage());
        }
        logger.info("[selectLetterIdByUserId] End");
        return null;
    }

    @Override
    public LetterDTO selectLetterByLetterId(String letterId) {
        try {
            logger.info("[selectLetterByLetterId] Start");

            logger.info("[selectLetterByLetterId] letterId = " + letterId);

            if(letterId.length() < 20){
                throw new LetterException();
            }

            Integer decLetterId = Integer.valueOf(aes256.replaceDecodeDecryt(letterId));

            // 오픈데이트 검증 로직 시작
            LetterDTO letterDTO = rao.selectLetterByLetterId(decLetterId);
            Timestamp openDate = memberRAO.findOpenDate(Integer.parseInt(letterDTO.getUserId()));
            // 864000000L = 10일
//            if(openDate != null && (new Timestamp(System.currentTimeMillis()-864000000L)).before(openDate)){
            if(openDate != null && (new Timestamp(System.currentTimeMillis()-600000L)).before(openDate)){
                logger.warn("[selectLetterByLetterId] 행성 만료 전임.");
                throw new MemberException("행성 만료 전임.", -999);
            }
            // 오픈데이트 검증 로직 끝

            rao.updateToReadLetter(decLetterId);
            letterDTO.setLetterId(aes256.encryptEncodeReplace(letterDTO.getLetterId()));

            logger.info("[selectLetterByLetterId] End");
            return letterDTO;
        }catch(LetterException le){
            logger.warn("[selectLetterByLetterId] LetterException = " + le.getMessage());
        }catch (Exception e){
            logger.warn("[selectLetterByLetterId] Exception = " + e.getMessage());
        }
        logger.info("[selectLetterByLetterId] End");
        return null;
    }

    @Override
    public List<StickerDTO> findStickerByLetterId(String letterId) throws LetterException {
        try{
            logger.info("[findStickerByLetterId] Start");

            logger.info("[findStickerByLetterId] letterId = " + letterId);

            if(letterId.length() < 20){
                throw new LetterException();
            }

            logger.info("[findStickerByLetterId] End");
            return rao.selectStickerByLetterId(Integer.valueOf(aes256.replaceDecodeDecryt(letterId)));
        }catch (LetterException le){
            logger.warn("[findStickerByLetterId] LetterException = " + le.getMessage());
        }catch (Exception e){
            logger.warn("[findStickerByLetterId] Exception = " + e.getMessage());
        }
        logger.info("[findStickerByLetterId] End");
        return null;
    }

    @Override
    public String blockByLetterId(String letterId) throws LetterException {
        try{
            logger.info("[blockByLetterId] Start");

            logger.info("[blockByLetterId] letterId = " + letterId);

            if(letterId.length() < 20){
                throw new LetterException();
            }
            rao.blockByLetterId(Integer.valueOf(aes256.replaceDecodeDecryt(letterId)));

            logger.info("[blockByLetterId] End");
            return letterId;
        }catch (LetterException le){
            logger.warn("[blockByLetterId] LetterException = " + le.getMessage());
        }catch (Exception e){
            logger.warn("[blockByLetterId] Exception = " + e.getMessage());
        }
        return null;
    }
}