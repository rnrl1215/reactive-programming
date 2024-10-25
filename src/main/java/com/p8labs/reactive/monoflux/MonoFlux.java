package com.p8labs.reactive.monoflux;

import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class MonoFlux {

    public List<String> FluxReturnStringList() {
        List<String> copyList = new ArrayList<>();
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(originList);
        seq2.subscribe(copyList::add);
        return copyList;
    }


    public List<String> FluxEx2ReturnListWithError() {
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

    public List<String> MonoEx() {
        List<String> copyList = new ArrayList<>();
        Mono<String> mono = Mono.just("foo");
        mono.subscribe(copyList::add);
        return copyList;
    }
}
