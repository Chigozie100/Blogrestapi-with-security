package com.springBoot.restfulApiBlogApp.service.impl;

import com.springBoot.restfulApiBlogApp.entity.Comment;
import com.springBoot.restfulApiBlogApp.entity.Post;
import com.springBoot.restfulApiBlogApp.exception.ResourceNotFoundException;
import com.springBoot.restfulApiBlogApp.payload.CommentDto;
import com.springBoot.restfulApiBlogApp.repository.CommentRepository;
import com.springBoot.restfulApiBlogApp.repository.PostRepository;
import com.springBoot.restfulApiBlogApp.service.CommentService;
import com.springBoot.restfulApiBlogApp.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Override
    public CommentDto createComment(Long post_id, CommentDto commentDto) {
       Post post= postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("post", "id", post_id));
       //dto to entity
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);
       Comment newComment = commentRepository.save(comment);

       //entity to dto
        return Mapper.mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comment=commentRepository.findAll();
        return comment.stream().map((comment1) -> Mapper.mapToDto(comment1)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment=commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment", "id", id));
        return Mapper.mapToDto(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto comment) {
        Comment comment1 =commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment", "id", id));
        comment1.setName(comment.getName());
        comment1.setEmail(comment.getEmail());
        comment1.setBody(comment.getBody());
        Comment newComment =commentRepository.save(comment1);
        return Mapper.mapToDto(newComment);
    }
}
