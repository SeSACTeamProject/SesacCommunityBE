package com.everysesac.backend.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class Curriculum {

    @Column(nullable = false)
    private String curriculumName;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

}
