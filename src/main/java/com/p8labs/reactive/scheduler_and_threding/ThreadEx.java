package com.p8labs.reactive.scheduler_and_threding;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ThreadEx {
    public void testFluxInSingleThread() throws InterruptedException {
        final Flux<String> flux = Flux.just("FIRST ","SECOND ","THIRD ");
        String name = Thread.currentThread().getName();
        log.info("THREAD NAME: {}", name);
        Thread t = new Thread(() -> flux
                .map(msg -> msg)
                .subscribe(v ->
                        log.info("THREAD NAME: {} data: {}",Thread.currentThread().getName(),v)

                )
        );
        t.start();
        t.join();
    }

    public void testFluxInMultyThread() throws InterruptedException {

        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux.just("FIRST ","SECOND ","THIRD ");

        flux.map(msg -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return msg;})
                .subscribe(v -> log.info("THREAD NAME: {} data: {}",Thread.currentThread().getName(),v));

        Thread.sleep(10000);

    }
}
