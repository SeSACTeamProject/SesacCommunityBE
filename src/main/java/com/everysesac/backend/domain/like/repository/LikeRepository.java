package com.everysesac.backend.domain.like.repository;

import com.everysesac.backend.domain.like.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
