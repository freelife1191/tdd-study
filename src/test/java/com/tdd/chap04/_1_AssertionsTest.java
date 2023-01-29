package com.tdd.chap04;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _1_AssertionsTest {

    @Test
    void assertEqualsMethod() {
        LocalDate dateTime1 = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now();
        assertEquals(dateTime1, dateTime2);
    }

}
