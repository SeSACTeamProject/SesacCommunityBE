package com.everysesac.backend.domain.comment.controller;

import com.everysesac.backend.domain.auth.dto.CustomUserDetails;
import com.everysesac.backend.domain.comment.dto.request.CommentCreateRequestDTO;
import com.everysesac.backend.domain.comment.dto.request.CommentUpdateRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.service.CommentServiceImpl;
import com.everysesac.backend.domain.user.service.UserService;
import com.everysesac.backend.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentServiceImpl commentService;
    private final UserService userService;


    @PostMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> register(@Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO, @AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long postId) {

        String username = userDetails.getUsername();
        Long userId = userService.findUserIdByUsername(username);
        commentCreateRequestDTO.setUserId(userId);
        commentCreateRequestDTO.setPostId(postId);
        CommentResponseDTO register = commentService.register(commentCreateRequestDTO);

        ApiResponse<CommentResponseDTO> apiResponse = new ApiResponse<>(
                "success",
                201,
                "comment created."
        );
        apiResponse.setData(register);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> modify(@Valid @RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO,@PathVariable Long postId,@PathVariable Long commentId) {
        commentUpdateRequestDTO.setCommentId(commentId);
        commentUpdateRequestDTO.setPostId(postId);
        CommentResponseDTO commentResponseDTO = commentService.modify(commentUpdateRequestDTO);
        ApiResponse<CommentResponseDTO> apiResponse = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Request was successful."
        );
        apiResponse.setData(commentResponseDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> remove(@PathVariable Long commentId,@PathVariable Long postId) {
        commentService.delete(commentId,postId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Delete was successful."
        );
        return ResponseEntity.ok(apiResponse);
    }


}
