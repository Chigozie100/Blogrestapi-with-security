package com.springBoot.restfulApiBlogApp.controller;

import com.springBoot.restfulApiBlogApp.payload.CommentDto;
import com.springBoot.restfulApiBlogApp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/createComment/{id}")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "id") Long post_id, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(post_id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllComments")
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/getCommentById/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCommentById/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name = "id") Long id){
        commentService.deleteById(id);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }

    @PutMapping("/updateComment/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "id") Long id, @Valid
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(id, commentDto), HttpStatus.OK);
    }

}
