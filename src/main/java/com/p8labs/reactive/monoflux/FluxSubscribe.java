package com.p8labs.reactive.monoflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class FluxSubscribe {

    public void sub1Ex() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> flux = Flux.fromIterable(originList);
        flux.map(this::upper).subscribe();
    }

    public void sub2Ex() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> flux = Flux.fromIterable(originList);
        flux.map(this::upper)
                .subscribe(word -> log.info("PRINT ::: {}", word));
    }

    public void sub2ExWithException() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> flux = Flux.fromIterable(originList);
        flux.map(this::upperWithException)
                .subscribe(word -> log.info("PRINT ::: {}", word));
    }

    public void sub3ex() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> flux = Flux.fromIterable(originList);
        flux.map(this::upperWithException)
                .subscribe(word -> log.info("PRINT ::: {}", word),
                           word -> log.info("Find error!!!!!"));
    }

    public void sub4ex() {
        List<String> originList = Arrays.asList("foo", "bar", "foobar");
        Flux<String> flux = Flux.fromIterable(originList);
        flux.map(this::upper)
                .subscribe(word -> log.info("PRINT ::: {}", word),
                           word -> log.info("Find error!!!!!"),
                           () -> log.info("SUCCESS!!!"));
    }

    public String upper(String word) {
        log.info("Call upper function, INPUT VALUE {}", word);
        return word.toUpperCase(Locale.ROOT);
    }


    public String upperWithException(String word) {
        if (word.equalsIgnoreCase("bar")) {
            throw new IllegalArgumentException("Throw error!!!!");
        }
        log.info("Call upper function, INPUT VALUE {}", word);
        return word.toUpperCase(Locale.ROOT);
    }
}
