package com.everysesac.backend.domain.comment.service;

import com.everysesac.backend.domain.comment.dto.request.CommentRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.entity.Comment;
import com.everysesac.backend.domain.comment.repository.CommentQueryRepository;
import com.everysesac.backend.domain.comment.repository.CommentRepository;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentQueryRepository commentQueryRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    public CommentResponseDTO registerComment(CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.findById(commentRequestDTO.getPostId()).orElseThrow(() -> new NoSuchElementException("게시물이 존재하지 않습니다."));
        post.addComment();
        Comment comment = Comment.builder().content(commentRequestDTO.getContent()).post(post).build();
        Comment saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentResponseDTO.class);
    }

    public CommentResponseDTO modifyComment(CommentRequestDTO commentRequestDTO) {
        postRepository.findById(commentRequestDTO.getPostId()).orElseThrow(() -> new NoSuchElementException("게시물이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentRequestDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        comment.changeContent(commentRequestDTO.getContent());
        Comment saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentResponseDTO.class);
    }






}
