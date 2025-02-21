package com.everysesac.backend.domain.comment.service;

import com.everysesac.backend.domain.comment.dto.request.CommentRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.entity.Comment;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Slf4j
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Test
    @Commit
    void registerCommentTest() {
        CommentRequestDTO commentRequestDTO = CommentRequestDTO.builder().content("dsadsasd").postId(205L).build();
        CommentResponseDTO register = commentService.registerComment(commentRequestDTO);
        log.info("{}",register);

    }

    @Test
    @Commit
    void modifyCommentTest() {
        CommentRequestDTO commentRequestDTO = CommentRequestDTO.builder().content("change Text").postId(205L).commentId(1L).build();
        CommentResponseDTO register = commentService.modifyComment(commentRequestDTO);
        log.info("{}",register);
    }

    @Test
    void deleteCommentTest() {
        commentService.deleteComment(205L,1L);
        
    }

}