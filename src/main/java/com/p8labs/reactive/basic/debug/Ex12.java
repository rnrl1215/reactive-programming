package com.p8labs.reactive.basic.debug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Ex12 {
    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
    }

    public static void ex1() {

//        Hooks.onOperatorDebug();

        Flux.fromArray(new String[] {"BANANAS", "APPLES", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .map(fruits -> fruits.substring(0, fruits.length() -1))
                .map(fruits::get)
                .map(translate -> "맛 좋은 " + translate)
                .subscribe(
                        log::info,
                        error -> log.error("# onError: ", error)
                );

        try {
            Thread.sleep(100L);
        } catch (Exception e) {

        }

    }
}
