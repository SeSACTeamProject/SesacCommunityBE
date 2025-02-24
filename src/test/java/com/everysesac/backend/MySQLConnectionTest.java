package com.everysesac.backend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class MySQLConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            log.info("✅ MySQL 연결 성공!");
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            log.error("❌ MySQL 연결 실패: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /*
    @Test
    void testQueryExecution() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(result).isEqualTo(1);
        log.info("✅ MySQL 쿼리 테스트 성공!");
    }

     */
}
