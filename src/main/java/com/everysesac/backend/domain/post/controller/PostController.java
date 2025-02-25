package com.everysesac.backend.domain.post.controller;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostUpdateRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.service.PostService;
import com.everysesac.backend.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.everysesac.backend.domain.post.dto.request.PostCreateRequestDTO;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<PostResponseDTO>> studyList(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<PostResponseDTO> posts = postService.listPosts(pageRequestDTO);
        ApiResponse<PostResponseDTO> response = ApiResponse.<PostResponseDTO>builder()
                .status("success")
                .code(200)
                .message("Resource was successful.")
                .size(posts.getSize())
                .dtoList(posts.getDtoList())
                .build();
        return ResponseEntity.ok(response);
    }

    @Tag(name="POST API", description = "POST API")
    @Operation(summary = "post insert")
        @Parameter(name="title", description = "post title")
        @Parameter(name="content", description = "post content")
        @Parameter(name="postType", description = "STUDY, TEAM")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PostResponseDTO>> register(
            @RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO) {
        PostResponseDTO postResponse = postService.register(postCreateRequestDTO);
        ApiResponse<PostResponseDTO> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value()+"",
                201,
                "post created"
        );
        apiResponse.setData(postResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Tag(name="POST API", description = "POST API")
    @Operation(summary = "post update")
    @Parameter(name="title", description = "post title")
    @Parameter(name="content", description = "post content")
    @Parameter(name="postStatus", description = "IN_PROGRESS, COMPLETED")
    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequestDTO postUpdateRequestDTO) {
        PostResponseDTO postResponse = postService.modify(postUpdateRequestDTO, postId);
        ApiResponse<PostResponseDTO> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value()+"",
                HttpStatus.OK.value(),
                "Request was successful."
        );
        apiResponse.setData(postResponse);
        return ResponseEntity.ok(apiResponse);
    }

}
