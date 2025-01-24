package com.p8labs.reactive.schedulerThreading;

import com.p8labs.reactive.entity.Member;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PubSubOnInnerExTest {
    private PubSubOnInnerEx pubSubOnInnerEx = new PubSubOnInnerEx();


    @Test
    void whenNoneApplyScheduler() {
        List<Member> testMembers1 = getTestMembers();
        List<Member> testMembers2 = getTestMembers2();
        Flux<Flux<Member>> flux = pubSubOnInnerEx.testV1(testMembers1, testMembers2);
        flux.subscribe(
                Flux::subscribe
        );
    }


    @Test
    void whenApplyScheduler() throws InterruptedException {
        List<Member> testMembers1 = getTestMembers();
        List<Member> testMembers2 = getTestMembers2();
        Flux<Flux<Member>> flux = pubSubOnInnerEx.testV2(testMembers1, testMembers2);
        flux.subscribe(
                Flux::subscribe
        );

        Thread.sleep(10000);
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

    // TODO: 공통으로 변경
    List<Member> getTestMembers2() {

        Member member1 = new Member(4L, "GOLD", 10, LocalDateTime.now());
        member1.updateIsBanned(true);

        Member member2 = new Member(5L, "GOLD", 10, LocalDateTime.now());
        member2.updateIsBanned(false);

        Member member3 = new Member(6L, "TEST", 10, LocalDateTime.now());
        member3.updateIsBanned(false);

        return List.of(member1, member2, member3);
    }
}