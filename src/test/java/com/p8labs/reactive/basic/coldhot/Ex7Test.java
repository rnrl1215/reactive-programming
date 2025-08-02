package com.p8labs.reactive.basic.coldhot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ex7Test {

    @Autowired
    Ex7 ex7;

    @Test
    void testEx1() throws InterruptedException {
        ex7.ex1();
    }


    @Test
    void testEx2() throws InterruptedException {
        ex7.ex2();
    }

    @Test
    void testCacheTest() throws InterruptedException {
        ex7.exCacheTest();
    }
}