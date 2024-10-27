package com.p8labs.reactive.monoflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class monofluxTest {
    private MonoFluxBasic monoflux = new MonoFluxBasic();

    @Test
    void test() {
        List<String> list = monoflux.fluxEx1();
        Assertions.assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void convertUpperWithError() {
        List<String> list = monoflux.fluxEx2();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void fluxEx3Test() {
        String string =  monoflux.fluxEx3();
        Assertions.assertThat(string).isEqualTo("foo");
    }
}