package com.everysesac.backend.domain.post.controller;
import com.everysesac.backend.domain.auth.dto.CustomUserDetails;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostCreateRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostUpdateRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostReadResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.service.PostService;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.service.UserService;
import com.everysesac.backend.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post API", description = "APIs related to posts")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @Operation(summary = "Get Post List", description = "Retrieve a paginated list of posts.")
    @GetMapping
    public ResponseEntity<ApiResponse<PostResponseDTO>> variousList(PageRequestDTO pageRequestDTO) {
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
    public ResponseEntity<ApiResponse<PostResponseDTO>> register(@AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid PostCreateRequestDTO postCreateRequestDTO) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        postCreateRequestDTO.setUser(user);
        PostResponseDTO postResponse = postService.register(postCreateRequestDTO);
        ApiResponse<PostResponseDTO> apiResponse = new ApiResponse<>(
                "success",
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
    public ResponseEntity<ApiResponse<PostResponseDTO>> modify(
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequestDTO postUpdateRequestDTO) {
        PostResponseDTO postResponse = postService.modify(postUpdateRequestDTO, postId);
        ApiResponse<PostResponseDTO> apiResponse = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Request was successful."
        );
        apiResponse.setData(postResponse);
        return ResponseEntity.ok(apiResponse);
    }
    @Tag(name="POST API", description = "POST API")
    @Operation(summary = "post delete")
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<ApiResponse<Void>> remove(@PathVariable Long postId) {
        postService.softDelete(postId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Delete was successful."
        );
        return ResponseEntity.ok(apiResponse);
    }

    @Tag(name="POST API", description = "POST API")
    @Operation(summary = "post read")
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostReadResponseDTO>> read(@PathVariable Long postId) {
        PostReadResponseDTO postResponse = postService.readOne(postId);
        ApiResponse<PostReadResponseDTO> apiResponse = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Request was successful."
        );
        apiResponse.setData(postResponse);
        return ResponseEntity.ok(apiResponse);
    }

}
