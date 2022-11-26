package com.springBoot.restfulApiBlogApp.service;

import com.springBoot.restfulApiBlogApp.entity.Comment;
import com.springBoot.restfulApiBlogApp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long post_id, CommentDto commentDto);

    List<CommentDto> getAllComments();

    CommentDto getCommentById(Long id);

    void deleteById(Long id);

    CommentDto updateComment(Long id, CommentDto comment);
}
