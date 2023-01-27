package com.tdd.chap01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class _1_StringTest {

    @Test
    void substring() {
        String str = "abcde";
        assertEquals("cd", str.substring(2,4));
    }
}
