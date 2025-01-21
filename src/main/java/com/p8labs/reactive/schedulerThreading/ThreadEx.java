package com.p8labs.reactive.schedulerThreading;

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

        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler", 3);
        Scheduler scheduler2 = Schedulers.single();
        Scheduler scheduler3 = Schedulers.boundedElastic();
        Scheduler scheduler4 = Schedulers.immediate();

        final Flux<String> flux = Flux.just("FIRST ","SECOND ","THIRD ");

        flux.parallel().runOn(scheduler4)
                .map(msg -> {
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
