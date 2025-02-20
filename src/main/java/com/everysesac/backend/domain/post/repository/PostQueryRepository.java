package com.everysesac.backend.domain.post.repository;


import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.dto.response.QPostResponseDTO;
import com.everysesac.backend.domain.post.entity.PostType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.util.List;

import static com.everysesac.backend.domain.post.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<PostResponseDTO> listPagedPosts(String titleKeyword, String contentKeyword, Pageable pageable, PostType postType, String sortField, String sortDirection ) {
        if (sortField == null || sortField.isEmpty()) {
            sortField = "createdAt";
        }
        if (sortDirection == null || sortDirection.isEmpty()) {
            sortDirection = "desc";
        }

        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sortField, sortDirection);

        List<PostResponseDTO> content = queryFactory
                .select(new QPostResponseDTO(post.id, post.title, post.createdAt, post.postStatus, post.viewsCount, post.commentsCount, post.likesCount))
                .from(post)
                .where(contentContains(contentKeyword), titleContains(titleKeyword), postTypeEquals(postType))
                .orderBy(orderSpecifier, post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(contentContains(contentKeyword), titleContains(titleKeyword), postTypeEquals(postType));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
    private OrderSpecifier<?> getOrderSpecifier(String sortField, String sortDirection) {

        Order order = "asc".equalsIgnoreCase(sortDirection) ? Order.ASC : Order.DESC;

        return switch (sortField) {
            case "likesCount" -> new OrderSpecifier<>(order, post.likesCount);
            case "viewsCount" -> new OrderSpecifier<>(order, post.viewsCount);
            case "createdAt" ->
                    new OrderSpecifier<>(order, post.createdAt);
            default ->
                    new OrderSpecifier<>(Order.DESC, post.createdAt);
        };
    }
    private BooleanExpression contentContains(String contentKeyword) {
        return StringUtils.hasText(contentKeyword) ? post.content.containsIgnoreCase(contentKeyword) : null;
    }

    private BooleanExpression titleContains(String titleKeyword) {
        return StringUtils.hasText(titleKeyword) ? post.title.containsIgnoreCase(titleKeyword) : null;
    }

    private BooleanExpression postTypeEquals(PostType postType) {
        return postType != null ? post.postType.eq(postType) : null;
    }

}
