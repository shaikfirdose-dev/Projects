package com.springboot.blog.springbootblogrestapi.service.impl;

import com.springboot.blog.springbootblogrestapi.exception.BlogApiException;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.model.Comment;
import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.payload.CommentDto;
import com.springboot.blog.springbootblogrestapi.repository.CommentRepository;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment =mapTOEntity(commentDto);

        // get post by postId
        Post post=postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));

        comment.setPost(post);
        // save post into comment
        Comment newComment=commentRepo.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> listOfComment=commentRepo.findByPostId(postId);
        return listOfComment.stream().map(comment -> mapToDto((comment))).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commendId) {

        Post post=postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","id", postId));

        Comment comment=commentRepo.findById(commendId)
                .orElseThrow(() -> new ResourceNotFoundException("Cooment","commentId",commendId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not exists");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
        Post post=postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not exists");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment=commentRepo.save(comment);


        return mapToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post=postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not exists");
        }
        commentRepo.delete(comment);
        return;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto=modelMapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapTOEntity(CommentDto commentDto){
        Comment comment=modelMapper.map(commentDto, Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
