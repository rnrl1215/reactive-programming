package com.p8labs.reactive.monoflux;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class MonoFluxBasic {

    public List<String> fluxEx1() {
        List<String> copyList = new ArrayList<>();
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(originList);
        seq2.subscribe(copyList::add);
        return copyList;
    }


    public List<String> fluxEx2() {
        List<String> copyList = new ArrayList<>();
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(originList);
        seq2.map(i -> {
                    if (i.equals("foobar")) {
                        throw new RuntimeException("Error");
                    }
                    return i.toUpperCase(Locale.ROOT);
                })
                .subscribe(copyList::add);
        return copyList;
    }

    public List<String> monoEx1() {
        List<String> copyList = new ArrayList<>();
        Mono<String> mono = Mono.just("foo");
        mono.subscribe(copyList::add);
        String string = mono.block();
        return copyList;
    }

    public String fluxEx3() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(originList);
        return seq2.blockFirst();
    }

}
