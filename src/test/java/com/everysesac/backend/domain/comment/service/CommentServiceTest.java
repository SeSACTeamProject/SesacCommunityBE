package com.everysesac.backend.domain.comment.service;

import com.everysesac.backend.domain.comment.dto.request.CommentRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import lombok.extern.slf4j.Slf4j;
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

}