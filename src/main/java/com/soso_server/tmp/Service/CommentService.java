package com.soso_server.tmp.Service;

import com.soso_server.tmp.Board.*;

import java.util.List;

@org.springframework.stereotype.Service
public class CommentService {

    ICommentDAO dao;

    public void setDao(ICommentDAO dao) {
        this.dao = dao;
    }

    //댓글
    public void createComment(CommentDTO commentDTO){
        System.out.println("commentDTO = " + commentDTO);
        dao.insertComment(commentDTO);
    }

    public List<CommentDTO> findComment(Integer postNum){
        return dao.findCommentAll(postNum);
    }


}
