package com.everysesac.backend.domain.post.service;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.request.PostUpdateRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import org.springframework.stereotype.Service;
import com.everysesac.backend.domain.post.dto.request.PostCreateRequestDTO;

@Service
public interface PostService {
    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO);

    public PostResponseDTO register(PostCreateRequestDTO postCreateRequestDTO);

    public PostResponseDTO modify(PostUpdateRequestDTO postUpdateRequestDTO, Long postId);


}
