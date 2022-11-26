package com.springBoot.restfulApiBlogApp.service.impl;

import com.springBoot.restfulApiBlogApp.entity.Post;
import com.springBoot.restfulApiBlogApp.exception.ResourceNotFoundException;
import com.springBoot.restfulApiBlogApp.payload.PostDto;
import com.springBoot.restfulApiBlogApp.payload.PostResponse;
import com.springBoot.restfulApiBlogApp.repository.PostRepository;
import com.springBoot.restfulApiBlogApp.service.PostService;
import com.springBoot.restfulApiBlogApp.utils.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private ModelMapper mapper;
    @Override
    public PostDto createPost(PostDto post) {
        //convert dto entity
        Post post1 = Mapper.mapToEntity(post);
        Post newPost = postRepository.save(post1);

        //convert entity to dto
        PostDto postDto = Mapper.mapToDto(newPost);
        return postDto;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map((post)->Mapper.mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        return Mapper.mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);

        return Mapper.mapToDto(newPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> searchPost(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts;
    }
}
