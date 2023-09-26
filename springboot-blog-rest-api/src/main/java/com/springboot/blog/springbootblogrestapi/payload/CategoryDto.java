package com.springboot.blog.springbootblogrestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "CategoryDto model Information"
)
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
}
