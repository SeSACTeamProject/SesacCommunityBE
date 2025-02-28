package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.request.PostCreateRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostUpdateRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostReadResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostQueryRepository;
import com.everysesac.backend.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
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

        Post post = Post.builder()
                .postType(postCreateRequestDTO.getPostType())
                .title(postCreateRequestDTO.getTitle())
                .content(postCreateRequestDTO.getContent())
                .user(postCreateRequestDTO.getUser())
                .build();
        Post save = postRepository.save(post);
        // TODO : JWT 구현 이후 writer 추가 작업

        return modelMapper.map(save, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO modify(PostUpdateRequestDTO postCreateRequestDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new EntityNotFoundException("Invalid request parameters : "+postId));
        post.changeTitle(postCreateRequestDTO.getTitle());
        post.changeContent(postCreateRequestDTO.getContent());
        post.changePostStatus(postCreateRequestDTO.getPostStatus());

        return modelMapper.map(post, PostResponseDTO.class);
    }

    @Override
    public void softDelete(Long postId) {
        long updatedRows = postQueryRepository.softDelete(postId);
        if (updatedRows == 0) { // 변경된 데이터 수가 0일 때 예외처리
            throw new EntityNotFoundException("Invalid request parameters : "+postId);
        }
    }

    @Override
    public PostReadResponseDTO readOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("Invalid request parameters : "+postId));
        return modelMapper.map(post, PostReadResponseDTO.class);
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