package com.soso_server.tmp.Board;

import java.util.List;

public class CategoryDAO implements ICategoryDAO{

    private ICategoryDAO mapper;

    // root-context에서 연결
    public void setMapper(ICategoryDAO mapper) {
        this.mapper = mapper;
    }

    public List<CategoryDTO> findAll() {
        return mapper.findAll();
    }

}
