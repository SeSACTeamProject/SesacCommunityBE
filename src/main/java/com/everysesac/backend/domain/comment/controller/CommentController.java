package com.everysesac.backend.domain.comment.controller;

import com.everysesac.backend.domain.comment.dto.request.CommentRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.service.CommentService;
import com.everysesac.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> createComment(@Validated CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.registerComment(commentRequestDTO);
        ApiResponse<CommentResponseDTO> success = ApiResponse.<CommentResponseDTO>builder().
                status("success")
                .code(201)
                .message("댓글이 성공적으로 등록되었습니다.")
                .dto(commentResponseDTO)
                .build();
        return ResponseEntity.ok(success);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> updateComment(@Validated CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.modifyComment(commentRequestDTO);
        ApiResponse<CommentResponseDTO> success = ApiResponse.<CommentResponseDTO>builder().
                status("success")
                .code(200)
                .message("댓글이 성공적으로 수정되었습니다.")
                .dto(commentResponseDTO)
                .build();
        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> deleteComment(@PathVariable Long postId,@PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        ApiResponse<CommentResponseDTO> success = ApiResponse.<CommentResponseDTO>builder().
                status("success")
                .code(200)
                .message("댓글이 성공적으로 삭제되었습니다.")
                .build();
        return ResponseEntity.ok(success);
    }



}
