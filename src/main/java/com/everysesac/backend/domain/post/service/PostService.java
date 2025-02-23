package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.PostDTO;

public interface PostService {
    Long register(PostDTO postDTO);
}
