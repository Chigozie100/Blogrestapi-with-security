package com.springBoot.restfulApiBlogApp.repository;

import com.springBoot.restfulApiBlogApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
