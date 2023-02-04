package com.tdd.chap06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * - 파일에서 숫자를 읽어와 숫자의 합을 구한다
 * - 한 줄마다 한 개의 숫자를 포함한다
 */
public class _1_BaseballGameTest {

    @Test
    void exactMatch() {
        // 정답이 456인 상황
        BaseballGame game = new BaseballGame("456");
        // 실행
        Score score = game.guess("456");
        // 결과 확인
        assertEquals(3, score.strikes());
        assertEquals(0, score.balls());
    }

    @Test
    void noMatch() {
        // 정답이 123인 상황
        BaseballGame game = new BaseballGame("123");
        // 실행
        Score score = game.guess("456");
        // 결과 확인
        assertEquals(0, score.strikes());
        assertEquals(0, score.balls());
    }

    @Test
    void anyMatch() {
        // 정답이 123인 상황
        BaseballGame game = new BaseballGame("123");
        // 실행
        Score score = game.guess("321");
        // 결과 확인
        assertEquals(1, score.strikes());
        assertEquals(2, score.balls());
    }

    @Test
    void genGame_With_DupNumber_Then_Fail() {
        assertThrows(IllegalArgumentException.class,
            () -> new BaseballGame("110"));
    }
}
