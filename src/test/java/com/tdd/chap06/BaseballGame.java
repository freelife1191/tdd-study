package com.tdd.chap06;

import java.util.Arrays;

class BaseballGame {
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
        for (int i = 0; i < this.answer.length; i++) {
            int diffValue = diff[i];
            int answerValue = this.answer[i];
            if (answerValue == diffValue) strikes++;
            if (Arrays.stream(this.answer).filter(num -> answerValue != diffValue).anyMatch(num -> num == diffValue)) balls++;
        }
        return new Score(strikes, balls);
    }
}
