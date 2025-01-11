package com.p8labs.reactive.scheduler_and_threding;

import org.junit.jupiter.api.Test;

class ThreadExTest {
    ThreadEx threadEx = new ThreadEx();

    @Test
    void singleThread() throws InterruptedException {
        threadEx.testFluxInSingleThread();
    }

    @Test
    void multiThread() throws InterruptedException {
        threadEx.testFluxInMultyThread();
    }
}