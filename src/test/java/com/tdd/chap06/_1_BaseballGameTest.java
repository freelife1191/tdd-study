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

    private static class BaseballGame {
        private final int[] answer;
        public BaseballGame(String answer) {
            this.answer = getDiff(answer);
        }

        public Score guess(String solution) {
            return getScore(getDiff(solution));
        }

        private static int[] getDiff(String solution) {
            return Arrays.stream(solution.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        private Score getScore(int[] diff) {
            int strikes = 0;
            int balls = 0;
            for (int i = 0; i < this.answer.length ; i++) {
                int diffValue = diff[i];
                if (this.answer[i] == diffValue) strikes++;
                if (Arrays.stream(this.answer).anyMatch(num -> num == diffValue)) balls++;
            }
            return new Score(strikes, balls);
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
