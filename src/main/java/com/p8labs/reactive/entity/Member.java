package com.p8labs.reactive.entity;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Member extends MemberCommon
{
    private Long id;
    private String grade;
    private Integer point;
    private LocalDateTime registerDttm;

    public Member(Long id, String grade, Integer point, LocalDateTime registerDttm) {
        this.grade = grade;
        this.point = point;
        this.registerDttm = registerDttm;
        this.id = id;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }
}
