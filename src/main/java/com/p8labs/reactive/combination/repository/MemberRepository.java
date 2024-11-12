package com.p8labs.reactive.combination.repository;

import com.p8labs.reactive.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class MemberRepository {

    public Member findUser(long id) throws InterruptedException {
        log.info("Finding member ...");
        Thread.sleep(2000L);
        if (id > 4) return null;
        Member member = new Member("GOLD", 500, LocalDateTime.now());
        member.updateId(id);
        return member;
    }

    public Integer findRewardByGrade(long id) throws InterruptedException {
        log.info("Finding reward ...");

        Thread.sleep(2000L);
        int reward = 100;
        if (id == 1) {
            reward = 300;
        } else if (id == 2) {
            reward = 200;
        }

        return reward;
    }

}
