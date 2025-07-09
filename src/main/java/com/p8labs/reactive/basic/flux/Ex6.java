package com.p8labs.reactive.basic.flux;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Ex6 {
    public void ex6() {

        // justOrEmpty null 허용
        // concatWith 2개를 합쳐서 하나의 flux 로 만들어줌
        Flux<String> flux = Mono.justOrEmpty("Steve")
                .concatWith(Mono.justOrEmpty("jobs"));

        flux.subscribe(System.out::println);

        // Steve
        // jobs
    }

    public void ex7() {
        Flux.concat( // FLUX
                Flux.just("A", "B"),
                Flux.just("C", "D"),
                Flux.just("E", "F")
        ).collectList() // MONO
        .subscribe(System.out::println); // MONO
    }
}