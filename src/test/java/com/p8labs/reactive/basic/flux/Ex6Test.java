package com.p8labs.reactive.basic.flux;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ex6Test {

    @Autowired
    Ex6 ex6;

    @Test
    void testEx7() {
        ex6.ex7();
    }


}