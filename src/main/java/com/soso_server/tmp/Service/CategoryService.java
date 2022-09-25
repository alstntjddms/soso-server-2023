package com.soso_server.tmp.Service;

import com.soso_server.tmp.Board.*;

import java.util.List;

@org.springframework.stereotype.Service
public class CategoryService {

    ICategoryDAO dao;

    public void setDao(ICategoryDAO dao) {
        this.dao = dao;
    }

    public List<CategoryDTO> categoryFind() {
        return dao.findAll();
    }

}
