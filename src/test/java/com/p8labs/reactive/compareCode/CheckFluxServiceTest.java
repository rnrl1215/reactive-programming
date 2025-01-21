package com.p8labs.reactive.compareCode;

import com.p8labs.reactive.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@SpringBootTest
class CheckFluxServiceTest {

    @Autowired
    private ReactorCheckService reactorCheckService;


    @Test
    void checkBlocking() {
        Member member = new Member(1L, "GOLD",
                                     500,
                                           LocalDateTime.of(2010,11,11,10,10));

        long beforeTime = System.currentTimeMillis();

        boolean blocking = reactorCheckService.blocking(member);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertThat(blocking).isTrue();
        System.out.println(secDiffTime);
    }



    @Test
    void checkMonoBlocking() {
        Member member = new Member(1L, "GOLD",
                500,
                LocalDateTime.of(2010,11,11,10,10));

        long beforeTime = System.currentTimeMillis();

        Mono<Boolean> mono = reactorCheckService.monoBlocking(member);
        Boolean block = mono.block();

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertThat(block).isTrue();
        System.out.println(secDiffTime);
    }


    @Test
    void checkMono() {
        Member member = new Member(1L, "GOLD",
                500,
                LocalDateTime.of(2010,11,11,10,10));

        long beforeTime = System.currentTimeMillis();

        Mono<Boolean> mono = reactorCheckService.mono(member);
        Boolean block = mono.block();

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;

        Assertions.assertThat(block).isTrue();
        System.out.println(secDiffTime);
    }
}