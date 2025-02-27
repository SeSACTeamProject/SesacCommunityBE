package com.everysesac.backend;

import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.auth.entity.SnsDiv;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional //테스트 후 데이터 롤백 (DB에 반영 안 됨)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    void testSaveAndFindUser() {
        // Builder 패턴을 사용하여 User 객체 생성
        User user = User.builder()
                .name("홍길동")
                .email("hong@example.com")
                .password("password123")
                .phonenumber("01012345678")
                .lastNumber("5678")
                .role(Role.USER)  // 적절한 Role 추가
                .snsDiv(SnsDiv.KAKAO)  // 적절한 snsDiv 추가
                .build();

        // 사용자 저장
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isNotNull();
        log.info("✅ 저장된 사용자 ID: {}", savedUser.getId());

        // 사용자 조회
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("홍길동");
        assertThat(foundUser.getEmail()).isEqualTo("hong@example.com");

        log.info("✅ 사용자 조회 성공! 이름: {}, 이메일: {}", foundUser.getName(), foundUser.getEmail());
        log.info("✅ 생성일: {}", foundUser.getCreatedAt());
        log.info("✅ 수정일: {}", foundUser.getUpdatedAt());
    }
}
