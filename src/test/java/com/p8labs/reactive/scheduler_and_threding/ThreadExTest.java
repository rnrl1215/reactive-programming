package com.p8labs.reactive.scheduler_and_threding;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

class ThreadExTest {
    ThreadEx threadEx = new ThreadEx();

    @Test
    void singleThread() throws InterruptedException {
        threadEx.testFluxInSingleThread();
    }

    @Test
    void multiThread() throws InterruptedException {
        threadEx.testFluxInMultyThread();
    }

    @Test
    void test2() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    System.out.println("First map: " + Thread.currentThread().getName());
                    return  10 + i;
                })
                .subscribeOn(s)
                .map(i -> {
                    System.out.println("Second map: " + Thread.currentThread().getName());
                    return  "value " + i;
                });



        Thread thread = new Thread(() -> flux.subscribe(v -> System.out.println("END!!")));

        thread.run();
    }
}