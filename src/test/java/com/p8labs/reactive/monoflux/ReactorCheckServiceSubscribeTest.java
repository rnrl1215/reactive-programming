package com.p8labs.reactive.monoflux;

import org.junit.jupiter.api.Test;

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
}