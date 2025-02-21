package com.everysesac.backend.domain.comment.repository;

import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.dto.response.QCommentResponseDTO;
import com.everysesac.backend.domain.comment.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.everysesac.backend.domain.comment.entity.QComment.comment;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CommentResponseDTO> findComments(Long postId) {
        return queryFactory
                .select(new QCommentResponseDTO(comment.id, comment.content, comment.createdAt))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .fetch();
    }

}
