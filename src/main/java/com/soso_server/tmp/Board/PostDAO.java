package com.soso_server.tmp.Board;

import java.util.List;

public class PostDAO implements IPostDAO{

    private IPostDAO mapper;

    // root-context에서 연결
    public void setMapper(IPostDAO mapper) {
        this.mapper = mapper;
    }

    public int insert(PostDTO postDTO){

        return mapper.insert(postDTO);
//        return insertResult();
    }
    public void deletePost(Integer postNum) {
        mapper.deletePost(postNum);
    }
    public void deleteComment(Integer postNum){
        mapper.deleteComment(postNum);
    }
    public void update(PostDTO postDTO) {
        mapper.update(postDTO);
    }

    public List<PostDTO> findAll() {
        return mapper.findAll();
    }
    public PostDTO findOne(Integer postNum) {
        return mapper.findOne(postNum);
    }
    public List<PostDTO> findList(Integer index) {
        return mapper.findList(index);
    }

    public int insertResult(){
        return mapper.insertResult();
    };


}
