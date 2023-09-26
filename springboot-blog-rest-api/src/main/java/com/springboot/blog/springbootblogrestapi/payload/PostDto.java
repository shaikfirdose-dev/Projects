package com.springboot.blog.springbootblogrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {

    private Long id;
    // title should have atleast 2 character
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String tittle;
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}
