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
    public Long register(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        // TODO : 세션에서 user 데이터 받아서 post에 저장하는 부분 필요
        Long id = postRepository.save(post).getId();
        return id;
    }
}
