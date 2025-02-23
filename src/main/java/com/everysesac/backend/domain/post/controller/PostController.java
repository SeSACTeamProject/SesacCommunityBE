package com.everysesac.backend.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Tag(name="test api", description = "it's a test controller method")
    @Operation(summary = "post insert", description = "new post insertion")
        @Parameter(name="title", description = "post title")
        @Parameter(name="content", description = "post content")
        @Parameter(name="postType", description = "study or team")
    @ApiResponse(responseCode = "201", description = "insert successful")
    @GetMapping("/register")
    public String postTest() {
        return "test success";
    }
}
