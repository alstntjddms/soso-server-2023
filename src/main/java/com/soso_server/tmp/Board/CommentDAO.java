package com.soso_server.tmp.Board;
import java.util.List;

public class CommentDAO implements ICommentDAO{

    private ICommentDAO mapper;

    // root-context에서 연결
    public void setMapper(ICommentDAO mapper) {
        this.mapper = mapper;
    }

    public void insertComment(CommentDTO commentDTO) {
        mapper.insertComment(commentDTO);
    }

    public List<CommentDTO> findCommentAll(Integer postNum) {
        return mapper.findCommentAll(postNum);
    }

}
