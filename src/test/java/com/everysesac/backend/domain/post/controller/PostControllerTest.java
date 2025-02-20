package com.everysesac.backend.domain.post.controller;

import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import com.everysesac.backend.domain.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;


@SpringBootTest
@Transactional
@Slf4j
class PostControllerTest {
    @Autowired
    private PostRepository postRepository;
    @Test
    @Commit
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i->{
            Post post = Post.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .postStatus(PostStatus.IN_PROGRESS)
                    .postType(PostType.STUDY)
                    .likesCount(i)
                    .build();
            postRepository.save(post);
            log.info("{}", post.getId());
        });
        IntStream.rangeClosed(1, 100).forEach(i->{
            Post post = Post.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .postStatus(PostStatus.IN_PROGRESS)
                    .postType(PostType.PROJECT)
                    .build();
            postRepository.save(post);
            log.info("{}", post.getId());
        });
    }


}