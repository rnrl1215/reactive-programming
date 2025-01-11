package com.p8labs.reactive.scheduler_and_threding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerExTest {
    private SchedulerEx ex = new SchedulerEx();

    @Test
    void test() throws InterruptedException {
        ex.scheduler();
    }
}