package com.p8labs.reactive.basic.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Ex10 {

    public static void ex3() {
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15})
                .parallel()                   // 물리적 CPU 수에 맞게 사전에 분배하는 역할
                .runOn(Schedulers.parallel()) // 실제로 병렬 수행
                .subscribe(data -> log.info("# onNext: {}", data));

        try {
            Thread.sleep(100L);
        } catch (Exception e) {

        }
    }

    public static void ex4() {
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,13,15})
                .parallel(4) // 쓰레드 수 지정                   // 물리적 CPU 수에 맞게 사전에 분배하는 역할
                .runOn(Schedulers.parallel()) // 실제로 병렬 수행
                .subscribe(data -> log.info("# onNext: {}", data));

        try {
            Thread.sleep(100L);
        } catch (Exception e) {

        }
    }

}
