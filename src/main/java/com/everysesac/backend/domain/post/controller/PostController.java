package com.everysesac.backend.domain.post.controller;

import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostRequestRegisterDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.service.PostService;
import com.everysesac.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PostResponseDTO>> whatsList(@Validated PageRequestDTO pageRequestDTO) {
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PostResponseDTO>> createPost(@Validated PostRequestRegisterDTO postRequestRegisterDTO) {
        PostResponseDTO dto = postService.registerPost(postRequestRegisterDTO);
        ApiResponse<PostResponseDTO> response = ApiResponse.<PostResponseDTO>builder().
                status("success")
                .code(201)
                .message("Resource was successful.")
                .dto(dto)
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(@Validated PostRequestRegisterDTO postRequestRegisterDTO,@PathVariable("postId") Long postId) {
        postRequestRegisterDTO.setPostId(postId);
        PostResponseDTO dto = postService.modifyPost(postRequestRegisterDTO);
        ApiResponse<PostResponseDTO> response = ApiResponse.<PostResponseDTO>builder().
                status("success")
                .code(200)
                .message("Request was successful.")
                .dto(dto)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId")Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.builder().status("success").code(200).message("Delete was successful").build());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDTO>> findPost(@PathVariable("postId")Long postId) {
        PostResponseDTO post = postService.findPost(postId);
        return ResponseEntity.ok(ApiResponse.<PostResponseDTO>builder().status("success").code(200).message("Request was successful").dto(post).build());
    }




}
