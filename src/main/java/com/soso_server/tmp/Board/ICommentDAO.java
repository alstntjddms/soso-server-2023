package com.soso_server.tmp.Board;

import java.util.List;

public interface ICommentDAO {
    void insertComment(CommentDTO commentDTO);
    List<CommentDTO> findCommentAll(Integer postNum);
}
