package com.springboot.blog.springbootblogrestapi.repository;

import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);
}
