package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.payload.CommentDto;
import com.springboot.blog.springbootblogrestapi.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @Operation(
            summary = "Create Comment Rest API",
            description = "Create comment Rest API is used for saving comment in the database to the particular post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") long postId,
                                           @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comments By Post Id",
            description = "This Rest API is used for getting comments for the particular posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
    }
    @Operation(
            summary = "Get Comment Rest API",
            description = "Getting particular comment for particular posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable(value = "postId") long postId,
                                            @PathVariable(value = "commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);

    }

    @Operation(
            summary = "Update Comment Rest API",
            description = "Update Comment rest API is used for updating the comment in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> updateCommentById(@PathVariable(value = "postId") long postId,
                                               @PathVariable("commentId") long commentId,
                                               @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateCommentById(postId, commentId,commentDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Comment Rest API",
            description = "Delete Comment Rest API is used for deleting the comment from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable(value = "postId") long postId,
                                               @PathVariable(value = "commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }

}
