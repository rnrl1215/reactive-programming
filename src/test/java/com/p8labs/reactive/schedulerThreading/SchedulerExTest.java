package com.p8labs.reactive.schedulerThreading;

import org.junit.jupiter.api.Test;

class SchedulerExTest {
    private SchedulerEx ex = new SchedulerEx();

    @Test
    void test() throws InterruptedException {
        ex.scheduler();
    }
}