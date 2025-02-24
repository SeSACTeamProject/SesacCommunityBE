package com.everysesac.backend.global.util;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_flag", nullable = false)
    private boolean deleteFlag = false;


    public void changeDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        if (deleteFlag) {
            this.deletedAt = LocalDateTime.now();
        } else {
            this.deletedAt = null;
        }
    }

}

