package com.soso_server.tmp.Controller;

import com.soso_server.controller.LetterController;
import com.soso_server.tmp.BlogException;
import com.soso_server.tmp.Board.PostDTO;
import com.soso_server.tmp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    PostService service;

    @Autowired
    LetterController test;

    @GetMapping("/board")
    public List<PostDTO> findAll(){
        return service.findAll();
    }

    @PostMapping("/board")
    public ResponseEntity<?> create(@RequestBody PostDTO postDTO){

        try {
            int a = service.create(postDTO);
            return new ResponseEntity<>(a, HttpStatus.OK);
        }catch (BlogException be){
            return new ResponseEntity<>(be.toMap(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PatchMapping("/board")
    public void update(@RequestBody PostDTO postDTO) {
        service.update(postDTO);
    }

    @DeleteMapping("/board/{index}")
    public void delete(@PathVariable Integer index) {
        service.delete(index);
    }

    @GetMapping("/board/{index}")
    public PostDTO findOne(@PathVariable Integer index) {
        return service.findOne(index);
    }

    @GetMapping("/list/{index}")
    public List<PostDTO> getListPost(@PathVariable Integer index) {
        return service.findList(index);
    }

}
