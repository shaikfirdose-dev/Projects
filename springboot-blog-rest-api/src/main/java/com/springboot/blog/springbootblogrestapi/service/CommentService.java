package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto comment);

    public List<CommentDto> getCommentsByPostId(long postId);

    public CommentDto getCommentById(long postId, long commendId);

    public CommentDto updateCommentById(long postId, long commentId, CommentDto comment);

    public void deleteCommentById(long postId, long commentId);
}
