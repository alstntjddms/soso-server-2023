package com.soso_server.tmp.Controller;

import com.soso_server.tmp.Board.CategoryDTO;
import com.soso_server.tmp.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDTO> findAll(){
        System.out.println("CategoryController.findAll");
        return categoryService.categoryFind();
    }

}
