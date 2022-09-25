package com.soso_server.tmp.Board;

import java.util.List;

public interface IPostDAO {
    List<PostDTO> findAll();
    int insert(PostDTO postDTO);
    void deletePost(Integer postNum);
    void deleteComment(Integer postNum);
    void update(PostDTO postDTO);
    PostDTO findOne(Integer postNum);
    List<PostDTO> findList(Integer index);

    int insertResult();

}
