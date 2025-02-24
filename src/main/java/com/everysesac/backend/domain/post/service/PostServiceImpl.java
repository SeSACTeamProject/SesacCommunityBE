package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.PostDTO;
import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    @Override
    public PostDTO register(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        // TODO : JWT 구현 이후 writer 추가 작업
        return modelMapper.map(postRepository.save(post), PostDTO.class);
    }
}
