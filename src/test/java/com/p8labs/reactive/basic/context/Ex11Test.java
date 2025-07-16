package com.p8labs.reactive.basic.context;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ex11Test {

    @Autowired
    private Ex11 ex11;

    @Test
    void test() {
        ex11.ex1();
    }

}