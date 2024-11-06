package com.p8labs.reactive.combination.repository;

import com.p8labs.reactive.entity.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class MemberRepository {

    public Member findUser(long id) {
        if (id > 4) return null;
        Member member = new Member("GOLD", 500, LocalDateTime.now());
        member.updateId(id);
        return member;
    }

    public Integer findRewardByGrade(String grade) {
        int reward = 100;
        if (grade.equalsIgnoreCase("GOLD")) {
            reward = 300;
        } else if (grade.equalsIgnoreCase("SILVER")) {
            reward = 200;
        }
        return reward;
    }

}
