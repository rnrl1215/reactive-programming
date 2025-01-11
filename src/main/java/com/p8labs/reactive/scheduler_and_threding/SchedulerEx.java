package com.p8labs.reactive.scheduler_and_threding;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerEx {

    public void scheduler() throws InterruptedException {
        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler", 4);

        log.info("Scheduler started");
        Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {

                try {
                    Thread.sleep(1000);
                }catch (Exception e) {

                }
                    return 10 + i;

                })
                .subscribeOn(scheduler)
                .map(i -> "value " + i);


        Flux<String> flux2 = Flux
                .range(1, 2)
                .map(i -> {

                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {

                    }
                    return 20 + i;

                })
                .subscribeOn(scheduler)
                .map(i -> "2-value " + i);

        flux.subscribe(System.out::println);
        flux2.subscribe(System.out::println);
        flux2.zipWith(flux).subscribe(System.out::println);
        log.info("Scheduler end");
        Thread.sleep(5000);

    }
}
