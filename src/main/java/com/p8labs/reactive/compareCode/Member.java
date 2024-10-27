package com.p8labs.reactive.compareCode;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Member
{
    private String grade;

    private Integer point;

    private LocalDateTime registerDttm;

    public Member(String grade, Integer point, LocalDateTime registerDttm) {
        this.grade = grade;
        this.point = point;
        this.registerDttm = registerDttm;
    }
}
