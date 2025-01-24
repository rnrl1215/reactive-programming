package com.p8labs.reactive.schedulerThreading;

import com.p8labs.reactive.entity.Member;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PubSubOnExTest {

    private PubSubOnEx pubSubOnEx = new PubSubOnEx();

    @Test
    void test1() {
        Flux<Member> memberFlux = pubSubOnEx.testV1(getTestMembers());

        long beforeTime = System.currentTimeMillis();
        List<Member> filteredMembers = memberFlux.collectList().block();
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertEquals(1, filteredMembers.size());
        System.out.println(secDiffTime);
    }


    @Test
    void test2() {
        Flux<Member> memberFlux = pubSubOnEx.testV2(getTestMembers());

        long beforeTime = System.currentTimeMillis();
        List<Member> filteredMembers = memberFlux.collectList().block();
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertEquals(1, filteredMembers.size());
        System.out.println(secDiffTime);
    }

    @Test
    void test3() {
        Flux<Member> memberFlux = pubSubOnEx.testV3(getTestMembers());

        long beforeTime = System.currentTimeMillis();
        List<Member> filteredMembers = memberFlux.collectList().block();
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertEquals(1, filteredMembers.size());
        System.out.println(secDiffTime);
    }


    // TODO: 공통으로 변경
    List<Member> getTestMembers() {

        Member member1 = new Member(1L, "GOLD", 10, LocalDateTime.now());
        member1.updateIsBanned(true);

        Member member2 = new Member(2L, "GOLD", 10, LocalDateTime.now());
        member2.updateIsBanned(false);

        Member member3 = new Member(3L, "TEST", 10, LocalDateTime.now());
        member3.updateIsBanned(false);

        return List.of(member1, member2, member3);
    }
}