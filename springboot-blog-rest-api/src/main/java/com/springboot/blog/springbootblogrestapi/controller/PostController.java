package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import com.springboot.blog.springbootblogrestapi.service.impl.PostServiceImpl;
import com.springboot.blog.springbootblogrestapi.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @Operation(
            summary = "Create Post Rest API",
            description = "Create Post Rest API is used to save the post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get All Posts Rest API",
            description = "Get All Posts Rest API is used for getting all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping
    public ResponseEntity<?> getAllEntities(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize,sortBy, sortDir),HttpStatus.OK);
    }

    @Operation(
            summary = "Get Post Rest API",
            description = "Get Post Rest API is used to get particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    @Operation(
            summary = "Update Post Rest API",
            description = "Update Post Rest API used for update particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDto post, @PathVariable("id") long id){
        return new ResponseEntity<>(postService.updatePost(post,id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post Rest API",
            description = "Delete Post Rest API is used to delete the particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully!",HttpStatus.OK);
    }

    //build get posts by category rest api
    @Operation(
            summary = "Get Post Rest API By Category",
            description = "Get Post By Category Rest API is used to get particular post by passing category id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

}
