package com.soso_server.tmp.Service;

import com.soso_server.tmp.BlogException;
import com.soso_server.tmp.Board.*;

import java.util.List;

@org.springframework.stereotype.Service
public class PostService {

    IPostDAO dao;

    public void setDao(IPostDAO dao) {
        this.dao = dao;
    }

    public List<PostDTO> findAll(){
        return dao.findAll();
    }

    public PostDTO findOne(Integer postNum){
        return dao.findOne(postNum);
    }

    public int create(PostDTO postDTO) throws BlogException {

        try {
            if(postDTO.getTitle().length() > 10){
                throw new BlogException( "-1", "제목이 너무김");
            }else if(postDTO.getText().length() > 100){
                throw new BlogException("-2", "내용이 너무김");
            }else if(postDTO.getWriteBy().length() > 10){
                throw new BlogException("-3", "작성자가 너무김");
            }

            int newNum = dao.insertResult() + 1;

            postDTO.setNum(newNum);
            dao.insert(postDTO);

            return newNum;
        }catch (Exception e){
            throw new BlogException("-999", "글올리기 작업 실패");
        }

    }

    public void delete(Integer postNum) {
        //게시글 댓글 삭제
        dao.deleteComment(postNum);
        //게시글 삭제
        dao.deletePost(postNum);
    }

    public void update(PostDTO postDTO) {
        dao.update(postDTO);
    }

    public List<PostDTO> findList(Integer index) {
        return dao.findList(index*3);
    }

}
