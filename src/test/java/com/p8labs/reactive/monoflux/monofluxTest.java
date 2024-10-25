package com.p8labs.reactive.monoflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class monofluxTest {
    private MonoFlux monoflux = new MonoFlux();

    @Test
    void test() {
        List<String> list = monoflux.FluxReturnStringList();
        Assertions.assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void convertUpperWithError() {
        List<String> list = monoflux.FluxEx2ReturnListWithError();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }
}