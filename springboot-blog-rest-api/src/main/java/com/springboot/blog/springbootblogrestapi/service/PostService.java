package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto post);
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    public PostDto getPostById(Long id);
    public PostDto updatePost(PostDto post, long id);
    public void deletePost(long id);

    public List<PostDto> getPostsByCategory(Long categoryId);
}
