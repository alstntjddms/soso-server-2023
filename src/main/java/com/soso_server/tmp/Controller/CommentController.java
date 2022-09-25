package com.soso_server.tmp.Controller;

import com.soso_server.tmp.Board.CommentDTO;
import com.soso_server.tmp.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comment/{index}")
    public List<CommentDTO> findAll(@PathVariable Integer index){
        System.out.println("CommentController.findAll");
        return commentService.findComment(index);
    }

    @PostMapping("/comment")
    public void createComment(@RequestBody CommentDTO commentDTO) {
        System.out.println("CommentController.createComment");
        commentService.createComment(commentDTO);
    }

}
