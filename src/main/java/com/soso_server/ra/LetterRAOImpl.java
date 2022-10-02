package com.soso_server.ra;

import com.soso_server.dto.LetterDTO;
import com.soso_server.dto.StickerDTO;
import com.soso_server.ra.itf.LetterRAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LetterRAOImpl implements LetterRAO {

    private LetterRAO mapper;

    public void setMapper(LetterRAO mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LetterDTO> findAllLetter() {
        Long nowDate = System.currentTimeMillis();
        Timestamp timeStamp = new Timestamp(nowDate);

        LetterDTO letterDTO = new LetterDTO();
        letterDTO.setLetterId(1);
        letterDTO.setUserId(2);
        letterDTO.setLetterContent("bb");
        letterDTO.setLetterFont("cc");
        letterDTO.setLetterFontColor("dd");
        letterDTO.setLetterPaper("ee");
        letterDTO.setLetterWriter("ff");
        letterDTO.setLetterIcon("gg");
        letterDTO.setLetterWriteDate(timeStamp);
        List<LetterDTO> letterDTOS = new ArrayList<>();
        letterDTOS.add(letterDTO);
        return letterDTOS;
    }

    @Override
    public int registerLetter(LetterDTO letterDTO) {
//        return mapper.registerLetter();
        return 1;
    }

    @Override
    public int registerSticker(StickerDTO stickerDTO) {
//        return mapper.registerSticker();
        return 0;
    }

    @Override
    public void test1() {
        mapper.test1();
    }


}
