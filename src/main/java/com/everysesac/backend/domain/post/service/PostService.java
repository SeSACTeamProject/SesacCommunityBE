package com.everysesac.backend.domain.post.service;

import com.everysesac.backend.domain.post.dto.PostDTO;
import com.everysesac.backend.domain.post.entity.Post;

public interface PostService {
    PostDTO register(PostDTO postDTO);
}
