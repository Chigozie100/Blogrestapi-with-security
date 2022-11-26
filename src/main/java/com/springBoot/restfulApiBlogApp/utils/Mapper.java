package com.springBoot.restfulApiBlogApp.utils;

import com.springBoot.restfulApiBlogApp.entity.Comment;
import com.springBoot.restfulApiBlogApp.entity.Post;
import com.springBoot.restfulApiBlogApp.payload.CommentDto;
import com.springBoot.restfulApiBlogApp.payload.PostDto;
import org.modelmapper.ModelMapper;

public class Mapper {
    private ModelMapper mapper;

    //convert entity to dto
    public static PostDto mapToDto(Post post){
       // PostDto postDto = mapper.map(post, PostDto.class);
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //convert dto to entity
    public static Post mapToEntity(PostDto postDto){
       // Post post = mapper.map(postDto. Post.class)
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    //convert comment entity to dto
    public static CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    //convert commentdto to entity
    public static Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
