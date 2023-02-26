package com.tdd.chap08.testable;

import com.tdd.chap08.subs.Product;
import com.tdd.chap08.subs.Subscription;

import java.time.LocalDate;

import static com.tdd.chap08.subs.Grade.GOLD;

/**
 * 테스트하고 싶은 코드만 별도로 분리하면 테스트할 수 있다
 */
public class PointRule {
    static int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        // 이 값을 기준으로 구독이 끝났는지 확인
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}