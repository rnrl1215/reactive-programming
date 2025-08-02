package com.p8labs.reactive.basic.operator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ex14Test {

    @Autowired
    private Ex14 ex14;

    @Test
    public void ex6() throws InterruptedException {
        ex14.ex6();
    }

    @Test
    public void ex7() throws InterruptedException {
        ex14.ex7();
    }
}