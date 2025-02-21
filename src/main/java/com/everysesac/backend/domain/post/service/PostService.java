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

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO) {
        log.info("{}", pageRequestDTO);
        Page<PostResponseDTO> posts = postQueryRepository.listPagedPosts(pageRequestDTO.getTitleKeyword(), pageRequestDTO.getContentKeyword(), pageRequestDTO.getPageable(), pageRequestDTO.getPostType(), pageRequestDTO.getSortField(), pageRequestDTO.getSortDirection(),pageRequestDTO.getPostStatus());
        return PageResponseDTO.<PostResponseDTO>withAll().
                dtoList(posts.getContent()).
                pageRequestDTO(pageRequestDTO).
                total((int) posts.getTotalElements()).
                build();
    }

    public PostResponseDTO registerPost(PostRequestRegisterDTO postRequestRegisterDTO) {
        Post post = modelMapper.map(postRequestRegisterDTO, Post.class);
        Post savePost = postRepository.save(post);
        return modelMapper.map(savePost, PostResponseDTO.class);
    }

    public PostResponseDTO modifyPost(PostRequestRegisterDTO postRequestRegisterDTO) {
        Post post = postRepository.findById(postRequestRegisterDTO.getPostId()).orElseThrow(()->new NoSuchElementException("해당 게시물이 존재하지 않습니다."));
        post.changePostStatus(postRequestRegisterDTO.getPostStatus());
        post.changeContent(postRequestRegisterDTO.getContent());
        post.changeTitle(postRequestRegisterDTO.getTitle());
        post.changePostType(postRequestRegisterDTO.getPostType());
        postRepository.save(post);
        return modelMapper.map(post, PostResponseDTO.class);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 게시물이 존재하지 않습니다."));
        post.changeDeleteFlag(true);
        postRepository.save(post);
    }

    public PostResponseDTO findPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 게시물이 존재하지 않습니다."));
        return modelMapper.map(post, PostResponseDTO.class);
    }
}
