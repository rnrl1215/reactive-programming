package com.p8labs.reactive.basic.operator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class Ex14 {
    public void ex6() throws InterruptedException {
        // just 는 hot 으로 한번 방출 되면 해당 데이터를 계속 사용한다.
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("#OnNext just1: {}", data));
        deferMono.subscribe(data -> log.info("#OnNext defer1: {}", data));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("#OnNext just2: {}", data));
        deferMono.subscribe(data -> log.info("#OnNext defer2: {}", data));
    }

    public void ex6_1() throws InterruptedException {
        // just 는 hot 으로 한번 방출 되면 해당 데이터를 계속 사용한다.
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("#OnNext just1: {}", data));
        deferMono.subscribe(data -> log.info("#OnNext defer1: {}", data));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("#OnNext just2: {}", data));
        deferMono.subscribe(data -> log.info("#OnNext defer2: {}", data));
    }

    public void ex7() throws InterruptedException {
        Mono.justOrEmpty("")
                .delayElement(Duration.ofSeconds(3))
                .switchIfEmpty(callDefault()) // 호출은 되지만 데이터는 덮어 씌우지 않음
                .switchIfEmpty(Mono.defer(() -> callDefault())) // 호출도 안됨 -> 성능을 높일수 있다.
                .subscribe(data -> log.info("#OnNext !!!!!!!!!!!: {}", data));

        Thread.sleep(4000);
    }

    public Mono<String> callDefault() {
        log.info("Call default !!!!!");
        return Mono.just(UUID.randomUUID().toString());
    }
}
