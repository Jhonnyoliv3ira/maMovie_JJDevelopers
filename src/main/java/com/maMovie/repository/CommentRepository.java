package com.maMovie.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maMovie.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
