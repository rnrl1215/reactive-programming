package com.p8labs.reactive.basic.context;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

@Component
@Slf4j
public class Ex11 {

    public static void ex1() {

        // deferContextual 컨텍스트에 쓰인 데이터를 읽는 용도
        // context 에 쓸때는 context 를 쓰지만 읽을때는 ContextView 로 쓴다.
        Mono.deferContextual(ctx ->
                        Mono
                                .just("Hello" + " " + ctx.get("firstName"))
                                .doOnNext(data -> log.info("# just doOnNext : {}", data))
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(data -> data + " " + ctx.get("lastName"))
                )
                .contextWrite(context -> context.put("lastName", "jobs"))
                .contextWrite(context -> context.put("firstName", "Steve"))
                .subscribe(data -> log.info("# onNext : {}", data));
        try {
            Thread.sleep(100L);
        }catch (Exception e) {
        }
    }

    public static void ex3() {
        String key1 = "company";
        String key2 = "firstName";
        String key3 = "lastName";

        Mono.deferContextual(
                ctx -> Mono.just(ctx.get(key1) + ", " + ctx.get(key2) + ", " + ctx.get(key3)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.putAll(Context.of(key2, "steve", key3, "jobs")
                        .readOnly()))
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext : {}", data));

        try {
            Thread.sleep(100L);
        }catch (Exception e) {
        }
    }


    public static void ex4() {
        // context 는 구독할때 하나의 컨텐스트가 연결이 된다.
        // context 는 Operator 체인의 아래에서 위로 전파된다.
        // 동일한 키에 대한 값을 중복해서 저장하면 가장 위쪽에 위치한 ContextWrite 저장한 값으로 덮어 쓴다.
    }
}
