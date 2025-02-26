package com.everysesac.backend.domain.user.repository;


import com.everysesac.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);

     User findByUsername(String username);

    Optional<User> findByEmail(String email);
}