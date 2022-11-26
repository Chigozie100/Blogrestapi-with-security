package com.springBoot.restfulApiBlogApp.controller;

import com.springBoot.restfulApiBlogApp.entity.Post;
import com.springBoot.restfulApiBlogApp.payload.PostDto;
import com.springBoot.restfulApiBlogApp.payload.PostResponse;
import com.springBoot.restfulApiBlogApp.service.PostService;
import com.springBoot.restfulApiBlogApp.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sizeNo", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortSize", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id ){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePost(id);
       return new ResponseEntity<>("post deleted", HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Post>> search(@RequestParam("query") String query){
        return new ResponseEntity<>(postService.searchPost(query), HttpStatus.OK);
    }

}
