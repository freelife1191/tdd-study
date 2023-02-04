package com.tdd.chap06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private static class BaseballGame {
        private final int[] answer;
        public BaseballGame(String answer) {
            this.answer = Arrays.stream(answer.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        public Score guess(String solution) {
            int[] diff = Arrays.stream(solution.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
            if (Arrays.equals(this.answer, diff)) {
                return new Score(3, 0);
            }
            return new Score(0, 0);
        }
    }

    private static class Score {

        private final int strikes;
        private final int balls;

        public Score(int strikes, int balls) {
            this.strikes = strikes;
            this.balls = balls;
        }

        public int strikes() {
            return this.strikes;
        }

        public int balls() {
            return this.balls;
        }
    }
}
