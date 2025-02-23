package com.everysesac.backend.domain.post.controller;

import com.everysesac.backend.domain.post.dto.PostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Tag(name="test api", description = "it's a test controller method")
    @Operation(summary = "post insert", description = "new post insertion")
        @Parameter(name="title", description = "post title")
        @Parameter(name="content", description = "post content")
        @Parameter(name="postType", description = "study or team")
//    @ApiResponse(responseCode = "201", description = "insert successful")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid PostDTO postDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 성공적으로 작성되었습니다.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert failed : " + e.getMessage());
        }
    }
}
