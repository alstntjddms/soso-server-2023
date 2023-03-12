package com.soso_server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soso_server.utils.AES256;
import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.exception.LetterException;
import com.soso_server.exception.MemberException;
import com.soso_server.ra.itf.LetterRAO;
import com.soso_server.service.itf.LetterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    LetterRAO rao;
    AES256 aes256 = new AES256();

    public void setRao(LetterRAO rao) {
        this.rao = rao;
    }

    @Override
    public List<LetterDTO> findLetterAll() {
        try{
            List<LetterDTO> letterDTOS = rao.findLetterAll();
            if(letterDTOS.size() > 0){
                return letterDTOS;
            }else{
                new LetterException("데이터 없음", 999);
            }
        }catch (Exception e){
            new LetterException("알수없는 편지조회 오류", -999);
        }
        return null;
    }

    @Override
    public String registerLetter(HashMap<String, Object> dto) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            HashMap Letter = mapper.convertValue(dto.get("letter"), HashMap.class);
//            Letter.replace("userId", aes256.decrypt(URLDecoder.decode(Letter.get("userId").toString().replaceAll("MSJSM", "%"), "UTF-8")));
            Letter.replace("userId", aes256.replaceDecodeDecryt(Letter.get("userId").toString()));
            LetterDTO letterDTO = mapper.convertValue(Letter, LetterDTO.class);
            letterDTO.setLetterReadYn(false);
            letterDTO.setLetterDelYn(false);
            rao.registerLetter(letterDTO);

            ArrayList<StickerDTO> stickerDTOs = mapper.convertValue(dto.get("sticker"), ArrayList.class);
            int maxLetterId = rao.selectMaxLetterId();
            for(int i=0; i<stickerDTOs.size(); i++){
                StickerDTO ss = mapper.convertValue(stickerDTOs.get(i), StickerDTO.class);;
                ss.setLetterId(maxLetterId);
                rao.registerSticker(ss);
            }
            return aes256.encrypt(String.valueOf(maxLetterId));
        }catch (Exception e){
            new LetterException("알수없는 편지 등록오류", -999);
        }
        return "";
    }

    @Override
    public List<LetterDTO> selectLetterIdByUserId(String userId){
        try{
            if(userId.length() < 20){
                throw new MemberException();
            }

            List<LetterDTO> result = new ArrayList<>();

//            int decUserId = Integer.parseInt(aes256.decrypt(URLDecoder.decode(userId.replaceAll("MSJSM", "%"), "UTF-8")));
            int decUserId = Integer.parseInt(aes256.replaceDecodeDecryt(userId));

            System.out.println("decUserId = " + decUserId);
            for(LetterDTO letterDTO : rao.selectLetterIdByUserId(decUserId)){

//                letterDTO.setLetterId(URLEncoder.encode(aes256.encrypt(letterDTO.getLetterId()), "UTF-8").replaceAll("%", "MSJSM"));
                letterDTO.setLetterId(aes256.encryptEncodeReplace(letterDTO.getLetterId()));

                letterDTO.setUserId("");
                letterDTO.setLetterContent("");
                letterDTO.setLetterFont("");
                letterDTO.setLetterFontColor("");
                letterDTO.setLetterPaper("");
                letterDTO.setLetterWriter("");
                result.add(letterDTO);
            }
            return result;
        }catch (MemberException me){
            new MemberException("잘못된 userId", -999);
        }catch (Exception e) {
            new LetterException("잘못된 편지 조회 요청", -999);
        }
        return null;
    }

    @Override
    public LetterDTO selectLetterByLetterId(String letterId) {
        try {
            if(letterId.length() < 20){
                throw new LetterException();
            }
            System.out.println("letterId = " + letterId);
//            return rao.selectLetterByLetterId(Integer.valueOf(aes256.decrypt(URLDecoder.decode(letterId.replaceAll("MSJSM", "%"), "UTF-8"))));
            return rao.selectLetterByLetterId(Integer.valueOf(aes256.replaceDecodeDecryt(letterId)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StickerDTO> findStickerByLetterId(String letterId) throws LetterException {
        if(letterId.length() < 20){
            throw new LetterException();
        }
        try{
//            return rao.selectStickerByLetterId(Integer.valueOf(aes256.decrypt(URLDecoder.decode(letterId.replaceAll("MSJSM", "%"), "UTF-8"))));
            return rao.selectStickerByLetterId(Integer.valueOf(aes256.replaceDecodeDecryt(letterId)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}