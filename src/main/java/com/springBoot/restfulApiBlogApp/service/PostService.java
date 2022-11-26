package com.springBoot.restfulApiBlogApp.service;

import com.springBoot.restfulApiBlogApp.entity.Post;
import com.springBoot.restfulApiBlogApp.payload.PostDto;
import com.springBoot.restfulApiBlogApp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);

    List<Post> searchPost(String query);
}
