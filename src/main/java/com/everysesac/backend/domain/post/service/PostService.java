package com.everysesac.backend.domain.post.service;
import com.everysesac.backend.domain.post.dto.request.PageRequestDTO;
import com.everysesac.backend.domain.post.dto.response.PageResponseDTO;
import com.everysesac.backend.domain.post.dto.response.PostResponseDTO;
import org.springframework.stereotype.Service;
import com.everysesac.backend.domain.post.dto.PostDTO;

@Service
public interface PostService {
    public PageResponseDTO<PostResponseDTO> listPosts(PageRequestDTO pageRequestDTO);

    public PostDTO register(PostDTO postDTO);



}
