package com.p8labs.reactive.basic.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class Ex9
{
    public static void ex1() {
        int tasks = 6;
        Flux.create((FluxSink<String> sinks) -> { // 프로그래밍 방식으로 signal 전송
            IntStream.range(1, tasks)
                    .forEach(n -> sinks.next(doTasks(n))); // doTask 결과 리턴 후 방출
        })
                .subscribeOn(Schedulers.boundedElastic()) // main 쓰레드와 분리
                .doOnNext(n -> log.info("#create :{}", n))

                .publishOn(Schedulers.parallel()) // 쓰레드 신규 생성
                .map(result -> result + " success!")  // 추가 적으로 가공처리
                .doOnNext(n -> log.info("#map :{}", n))

                .publishOn(Schedulers.parallel()) // 쓰레드 신규 생성
                .subscribe(data -> log.info("onNext " + data));

        try {
            Thread.sleep(500L);
        } catch (Exception e) {

        }
    }

    public static String doTasks(int taskNumber) {
        return "task " + taskNumber + " result";
    }


    public static void ex2() {
        int tasks = 6;

        // 멀티 쓰레드에서 스레드 안정성을 보장 받을수 있다.
        Sinks.Many<String> unicastSink
                = Sinks.many().unicast().onBackpressureBuffer();

        IntStream.range(1, tasks)
                .forEach(n -> {

                    try {
                        new Thread(() -> {
                            unicastSink.emitNext( // 데이터 가공후 방출
                                                 // downstream emit
                                    doTasks(n),
                                    Sinks.EmitFailureHandler.FAIL_FAST
                                    );
                            log.info("# emitted :{}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (Exception e) {
                        log.error("error", e);
                    }
                });

        Flux<String> fluxView = unicastSink.asFlux();
        fluxView
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("#map :{}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("onNext " + data));

        try {
            Thread.sleep(200L);
        } catch (Exception e) {
        }
    }


    // many 와 mulit 차이
    public static void ex8() {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));

        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        // ERROR Sinks.many().unicast() sinks only allow a single Subscriber
//        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));
    }

    public static void ex9() {
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));
    }


    public static void ex10() {
        Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);

        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));

        replaySink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscribe :{}", data));
    }
}
