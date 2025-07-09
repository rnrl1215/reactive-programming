package com.p8labs.reactive.basic.coldhot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class Ex7 {

    void ex1() {
        Flux<String> coldFlux
                = Flux.fromIterable(List.of("A", "B", "C", "D"))
                .map(String::toLowerCase);

        coldFlux.subscribe(alpha -> log.info("#Sub1: {}", alpha));

        log.info("---------------------------------------------------");

        coldFlux.subscribe(alpha -> log.info("#Sub2: {}", alpha));
    }

    void ex2() throws InterruptedException {
        Flux<String> hotFlux
                = Flux.fromIterable(List.of("A", "B", "C", "D"))
                .delayElements(Duration.ofSeconds(1L))
                .share();

        // A B C D 구독
        hotFlux.subscribe(alpha -> log.info("#Sub1: {}", alpha));

        Thread.sleep(2500);
        log.info("---------------------------------------------------");

        // 위에서 구독된 시점 이후 C D 구독
        hotFlux.subscribe(alpha -> log.info("#Sub2: {}", alpha));

        Thread.sleep(3000);
    }


    public void exCacheTest() {
        // 값을 캐시 하여 계속 사용 단 호출 시점에만
        Mono<String> cache = exTest().cache();
        cache.subscribe(alpha -> log.info("#Sub1: {}", alpha));
        cache.subscribe(alpha -> log.info("#Sub2: {}", alpha));
    }


    Mono<String> exTest() {
        return Mono.just(UUID.randomUUID().toString());
    }
}
