package com.p8labs.reactive.basic.debug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ex12Test {
    @Autowired
    private Ex12 ex12;

    @Test
    public void test() {
        ex12.ex1();
    }

}