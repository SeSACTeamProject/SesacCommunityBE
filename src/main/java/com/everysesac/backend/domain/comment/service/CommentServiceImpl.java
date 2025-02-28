package com.everysesac.backend.domain.comment.service;

import com.everysesac.backend.domain.comment.dto.request.CommentCreateRequestDTO;
import com.everysesac.backend.domain.comment.dto.request.CommentUpdateRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.comment.entity.Comment;
import com.everysesac.backend.domain.comment.repository.CommentRepository;
import com.everysesac.backend.domain.post.dto.response.PostReadResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostRepository;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    public CommentResponseDTO register(CommentCreateRequestDTO commentCreateRequestDTO) {
        User user = userRepository.findById(commentCreateRequestDTO.getUserId()).orElseThrow();
        Post post = postRepository.findById(commentCreateRequestDTO.getPostId()).orElseThrow();
        post.increaseCommentsCount();
        Comment comment = Comment.builder().user(user).post(post).content(commentCreateRequestDTO.getContent()).build();
        Comment save = commentRepository.save(comment);
        CommentResponseDTO commentResponseDTO = modelMapper.map(save, CommentResponseDTO.class);
        commentResponseDTO.setName(user.getName());
        return commentResponseDTO;
    }


    public CommentResponseDTO modify(CommentUpdateRequestDTO commentUpdateRequestDTO) {
        Comment comment = commentRepository.findById(commentUpdateRequestDTO.getCommentId()).orElseThrow();
        comment.changeContent(commentUpdateRequestDTO.getContent());
        CommentResponseDTO map = modelMapper.map(comment, CommentResponseDTO.class);
        map.setName(comment.getUser().getName());
        return map;
    }


    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);
        comment.getPost().decreaseCommentsCount();
    }


}
