package com.p8labs.reactive.entity;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Member
{
    private Long id;
    private String grade;
    private Integer point;
    private LocalDateTime registerDttm;
    private Integer reward;

    public Member(String grade, Integer point, LocalDateTime registerDttm) {
        this.grade = grade;
        this.point = point;
        this.registerDttm = registerDttm;
    }

    public void updateId(long id) {
        this.id = id;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }
}
