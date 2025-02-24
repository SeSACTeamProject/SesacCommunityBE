package com.everysesac.backend.domain.post.service;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import com.everysesac.backend.domain.post.repository.PostQueryRepository;
import com.everysesac.backend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.everysesac.backend.domain.post.dto.PostDTO;
import org.modelmapper.ModelMapper;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO) {
        Page<PostResponseDTO> posts = postQueryRepository.listPagedPosts(pageRequestDTO.getTitleKeyword(), pageRequestDTO.getContentKeyword(), pageRequestDTO.getPageable(), pageRequestDTO.getPostType(), pageRequestDTO.getSortField(), pageRequestDTO.getSortDirection());
        return PageResponseDTO.<PostResponseDTO>withAll().
                dtoList(posts.getContent()).
                pageRequestDTO(pageRequestDTO).
                total((int) posts.getTotalElements()).
                build();
    }
  
    public PostDTO register(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        // TODO : JWT 구현 이후 writer 추가 작업
        return modelMapper.map(postRepository.save(post), PostDTO.class);
    }
}
