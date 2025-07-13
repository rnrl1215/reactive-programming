package com.p8labs.reactive.basic.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Ex8 {

    public static void ex1() {
        Flux.range(1, 10)
                .doOnRequest(data -> log.info("# doOnRequest: {}", data))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(4);
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# hookOnNext: {}", value);
                        request(4);
                    }
                });
    }

    public static void ex2() {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnRequest(data -> log.info("# doOnRequest: {}", data))
                .publishOn(Schedulers.parallel())
                .subscribe(
                        i -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException e) {}
                            log.info("# doOnNext: {}", i);
                        },
                        error -> log.info("# error: {}", error)
                );

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
