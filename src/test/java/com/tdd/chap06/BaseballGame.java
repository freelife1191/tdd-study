package com.tdd.chap06;

import java.util.Arrays;

class BaseballGame {
    private final int[] answer;

    public BaseballGame(String answer) {
        this.answer = toIntArray(answer);
    }

    public Score guess(String solution) {
        return getScore(toIntArray(solution));
    }

    private static int[] toIntArray(String str) {
        int[] intArray = Arrays.stream(str.split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
        validationCheck(intArray);
        return intArray;
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

    private static void validationCheck(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j])
                    throw new IllegalArgumentException("not allowed duplicate number");
            }
        }
    }
}
