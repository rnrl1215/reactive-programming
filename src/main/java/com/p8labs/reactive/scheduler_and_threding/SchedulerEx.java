package com.p8labs.reactive.scheduler_and_threding;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SchedulerEx {

    public void scheduler() {
        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler", 4);

        Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .subscribeOn(scheduler)
                .map(i -> "value " + i);

        flux.subscribe(System.out::println);

    }
}
