package com.springBoot.restfulApiBlogApp.repository;

import com.springBoot.restfulApiBlogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE " +
    "p.title Like CONCAT('%', :query, '%') " +
            "or p.description LIKE CONCAT('%', :query, '%' )")
    List<Post> searchPosts(String query);
}
