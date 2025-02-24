package com.everysesac.backend.domain.post.controller;

import com.everysesac.backend.domain.post.dto.PostDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.service.PostService;
import com.everysesac.backend.global.dto.ApiResponse;
import com.everysesac.backend.global.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Tag(name="test api", description = "it's a test controller method")
    @Operation(summary = "post insert", description = "new post insertion")
        @Parameter(name="title", description = "post title")
        @Parameter(name="content", description = "post content")
        @Parameter(name="postType", description = "study or team")
//    @ApiResponse(responseCode = "201", description = "insert successful")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PostDTO>> register(@Valid PostDTO postDTO) {
        PostDTO postResponse = postService.register(postDTO);
        ApiResponse<PostDTO> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value()+"",
                201,
                "post created"
        );
        apiResponse.setDto(postResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
