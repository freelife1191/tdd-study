package com.tdd.chap04;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class _1_AssertionsTest {

    @Test
    void assertEqualsMethod() {
        LocalDate dateTime1 = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now();
        assertEquals(dateTime1, dateTime2);
    }

    @Disabled
    @Test
    void failMethod() {
        try {
            AuthService authService = new AuthService();
            authService.authenticate(null,null);
            fail(); // 이 지점에 다다르면 fail() 메서드는 테스트 실패 에러를 발생
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void assertThrowsTest() {
        // assertThrows() 메서드는 발생한 익셉션 객체를 리턴한다
        assertThrows(IllegalArgumentException.class,
            () -> {
                AuthService authService = new AuthService();
                authService.authenticate(null,null);
            });

        // 발생한 익셉션을 이용해서 추가로 검증이 필요하면 assertThrows() 메서드가 리턴한 익셉션 객체를 사용하면 된다
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
            () -> {
                AuthService authService = new AuthService();
                authService.authenticate(null,null);
            });
        assertTrue(thrown.getMessage().contains("id"));
    }

}
