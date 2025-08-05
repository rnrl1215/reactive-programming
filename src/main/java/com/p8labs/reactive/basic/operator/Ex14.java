package com.p8labs.reactive.basic.operator;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.function.Tuples;

import java.awt.print.Pageable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public void ex9() throws InterruptedException {
        Flux.generate(() -> 0, (state, sink) -> {
            sink.next(state);
            if (state == 10) {
                sink.complete();
            }
            return ++state;
        }).subscribe(data -> log.info("#OnNext !!!!!!!!!!!!: {}", data));
    }

    public void ex10() throws InterruptedException {
        int dan = 3;
        Flux.generate(() -> Tuples.of(dan, 1),
                (state, sink) -> {
                    Integer t1 = state.getT1();
                    Integer t2 = state.getT2();
                    sink.next(t1 + "*" + t2 + "=" + (t1 * t2));

                    if (t2 == 9) {
                        sink.complete();
                    }

                    return Tuples.of(t1, t2 +1);
        }).subscribe(data -> log.info("#OnNext !!!!!!!!!!!!: {}", data));
    }

    static int size = 0;
    static int count = -1;

    public void ex12() {
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        // create 는 한번에 여러건의 데이터를 비동기 적으로 emit 할 수 있다.
        Flux.create((FluxSink<Integer> sink) -> {

            sink.onRequest( n -> { // request 호출시 해당 부분이 호출됨.

                // n 요청 받은 emit 수
                        try {
                            log.info("n?? {}", n);
                            Thread.sleep(1000);
                            for (int i = 0; i < n; i++) {
                                if (count >= 9) {
                                    sink.complete();
                                } else {
                                    count++;
                                    sink.next(source.get(count));
                                }
                            }
                        } catch (Exception e) {}
                    });

            sink.onDispose(() -> log.info("# clean up"));
        }).subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                log.info("Start subscribe");
                request(2); // 구독이 시작 되면 해당 함수 호출
            }

            @Override
            protected void hookOnNext(Integer value) {
                // 이후 정상 emit 되면 호출
                size++;
                log.info("# OnNext : {}", value);
                if (size == 2) {
                    request(2);
                    size = 0;
                }
            }

            @Override
            protected void hookOnComplete() {
                log.info("# onComplete ");
            }
        });
    }
}
