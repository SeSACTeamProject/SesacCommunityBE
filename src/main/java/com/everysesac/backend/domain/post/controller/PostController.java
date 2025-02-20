package com.everysesac.backend.domain.post.controller;

import com.everysesac.backend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    @GetMapping
    public void test() {
        log.info("test");
    }

}
