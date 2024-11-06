package com.p8labs.reactive.monoflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReactorCheckServiceSubscribeTest
{
    private FluxSubscribe fluxSubscribe = new FluxSubscribe();

    @Test
    void sub1ExTest() {
        fluxSubscribe.sub1Ex();
    }


    @Test
    void sub2ExTest() {
        fluxSubscribe.sub2Ex();
    }

    @Test
    void sub2ExWithExceptionTest() {
        fluxSubscribe.sub2ExWithException();
    }


    @Test
    void sub3Ex() {
        fluxSubscribe.sub3ex();
    }



    @Test
    void sub4Ex() {
        fluxSubscribe.sub4ex();
    }

    @Test
    void sub5Ex() {
        fluxSubscribe.sub5ex();
    }

    @Test
    void onError() {
        List<Integer> returnedList = fluxSubscribe.onError();
        Assertions.assertThat(returnedList.size()).isEqualTo(3);
    }
}