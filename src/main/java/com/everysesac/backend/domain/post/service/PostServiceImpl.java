package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.request.PostCreateRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostUpdateRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostQueryRepository;
import com.everysesac.backend.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Override
    public PostResponseDTO register(PostCreateRequestDTO postCreateRequestDTO) {
        Post post = modelMapper.map(postCreateRequestDTO, Post.class);
        // TODO : JWT 구현 이후 writer 추가 작업
        return modelMapper.map(postRepository.save(post), PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO modify(PostUpdateRequestDTO postCreateRequestDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("===== POST SERVICE : in method modify, post not fount : "+postId));

        post.changeTitle(postCreateRequestDTO.getTitle());
        post.changeContent(postCreateRequestDTO.getContent());
        post.changePostStatus(postCreateRequestDTO.getPostStatus());

        return modelMapper.map(post, PostResponseDTO.class);
    }

    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO) {
        Page<PostResponseDTO> posts = postQueryRepository.listPagedPosts(pageRequestDTO.getTitleKeyword(), pageRequestDTO.getContentKeyword(), pageRequestDTO.getPageable(), pageRequestDTO.getPostType(), pageRequestDTO.getSortField(), pageRequestDTO.getSortDirection());
        return PageResponseDTO.<PostResponseDTO>withAll().
                dtoList(posts.getContent()).
                pageRequestDTO(pageRequestDTO).
                total((int) posts.getTotalElements()).
                build();
    }

}