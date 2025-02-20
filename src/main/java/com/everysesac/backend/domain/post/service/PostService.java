package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostRequestRegisterDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostQueryRepository;
import com.everysesac.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO) {
        Page<PostResponseDTO> posts = postQueryRepository.listPagedPosts(pageRequestDTO.getTitleKeyword(), pageRequestDTO.getContentKeyword(), pageRequestDTO.getPageable(), pageRequestDTO.getPostType(), pageRequestDTO.getSortField(), pageRequestDTO.getSortDirection());
        return PageResponseDTO.<PostResponseDTO>withAll().
                dtoList(posts.getContent()).
                pageRequestDTO(pageRequestDTO).
                total((int) posts.getTotalElements()).
                build();
    }

    public PostResponseDTO register(PostRequestRegisterDTO postRequestRegisterDTO) {
        Post post = modelMapper.map(postRequestRegisterDTO, Post.class);
        Post savePost = postRepository.save(post);
        return modelMapper.map(savePost, PostResponseDTO.class);
    }

    public PostResponseDTO modify(PostRequestRegisterDTO postRequestRegisterDTO) {
        Post post = postRepository.findById(postRequestRegisterDTO.getPostId()).orElseThrow();
        post.changePostStatus(postRequestRegisterDTO.getPostStatus());
        post.changeContent(postRequestRegisterDTO.getContent());
        post.changeTitle(postRequestRegisterDTO.getTitle());
        post.changePostType(postRequestRegisterDTO.getPostType());
        postRepository.save(post);
        return modelMapper.map(post, PostResponseDTO.class);
    }


}
