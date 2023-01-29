package com.tdd.chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void assertAllTest() {
        /*
            assert 메서드는 실패하면 다음 코드를 실행하지 않고 바로 익셉션을 발생한다
            예를 들어 다음 코드는 첫 번째 assertEquals() 메서드에서 검증에 실패하기 때문에 그 시점에 AssertionFailedError를 발생한다
            따라서 두 번째 assertEquals() 메서드는 실행되지 않는다

            경우에 따라 일단 모든 검증을 실행하고 그중에 실패한 것이 있는지 확인하고 싶을 때 assertAll() 메서드를 사용

            assertAll() 메서드는 Executable 목록을 가변인자로 전달받아 각 Executable을 실행한다
            실행 결과로 검증에 실패한 코드가 있으면 그 목록을 모아서 에러 메시지로 보여준다
         */
        assertAll(
            () -> assertEquals(3, 5/2), // 검증 실패로 에러 발생
            () -> assertEquals(4, 2*2), // 이 코드는 실행되지 않음
            () -> assertEquals(6, 11/2)
        );
    }

}
