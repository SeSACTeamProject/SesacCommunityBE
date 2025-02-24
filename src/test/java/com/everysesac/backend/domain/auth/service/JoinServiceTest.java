package com.everysesac.backend.domain.auth.service;

import com.everysesac.backend.domain.auth.jwt.dto.JoinDTO;
import com.everysesac.backend.domain.auth.jwt.service.JoinService;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class JoinServiceTest {
    @Autowired
    private JoinService joinService;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void joinTest() {
        JoinDTO joinDTO = JoinDTO.builder().email("daa@naver.com").password("1234").phoneNumber("01011112222").username("korea1111").build();

        joinService.joinProcess(joinDTO);

        List<User> all = userRepository.findAll();
        User user = all.get(0);
        log.info("{},{},{},{}", user.getEmail(),user.getCreatedAt(),user.getPhonenumber(),user.getPassword());


    }

}