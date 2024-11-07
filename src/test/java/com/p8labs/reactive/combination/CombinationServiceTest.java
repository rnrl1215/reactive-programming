package com.p8labs.reactive.combination;

import com.p8labs.reactive.combination.repository.MemberRepository;
import com.p8labs.reactive.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CombinationServiceTest {

    @Autowired
    private CombinationService combinationService;

    @Test
    void test () {
        long beforeTime = System.currentTimeMillis();

        Mono<List<Member>> userWithReward = combinationService.findUserWithReward();
        List<Member> block = userWithReward.block();

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        System.out.println(secDiffTime);
    }

    @Test
    void test2 () {
        long beforeTime = System.currentTimeMillis();

        Mono<List<Member>> userWithReward = combinationService.findUserWithRewardWithExecutor();
        List<Member> block = userWithReward.block();

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        System.out.println(secDiffTime);
    }
}
