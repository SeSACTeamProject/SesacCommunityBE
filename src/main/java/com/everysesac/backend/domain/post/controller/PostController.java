package com.everysesac.backend.domain.post.controller;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
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
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.everysesac.backend.domain.post.dto.PostDTO;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

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
    @Tag(name="test api", description = "it's a test controller method")
    @Operation(summary = "post insert", description = "new post insertion")
        @Parameter(name="title", description = "post title")
        @Parameter(name="content", description = "post content")
        @Parameter(name="postType", description = "study or team")
    //@ApiResponse(responseCode = "201", description = "insert successful")

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PostDTO>> register(@Valid PostDTO postDTO) {
        PostDTO postResponse = postService.register(postDTO);
        ApiResponse<PostDTO> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value()+"",
                201,
                "post created"
        );
        apiResponse.setData(postResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
