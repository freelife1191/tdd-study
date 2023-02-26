package com.tdd.chap08.testable;

import com.tdd.chap08.subs.NoSubscriptionException;
import com.tdd.chap08.subs.Product;
import com.tdd.chap08.subs.ProductDao;
import com.tdd.chap08.subs.Subscription;
import com.tdd.chap08.subs.SubscriptionDao;
import com.tdd.chap08.subs.User;

import java.time.LocalDate;

import static com.tdd.chap08.subs.Grade.GOLD;

/**
 * [실행 시점에 따라 달라지는 결과]
 * 기능 구현이 섞여 있어 특정한 부분만 테스트하기 어려운 코드
 */
public class UserPointCalculator {
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    /**
     * 같은 테스트 코드라도 LocalDate.now()에 따라 실행 결과가 달라진다
     */
    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSubscriptionException();
        Product p = productDao.selectById(s.getProductId());
        // 현재 시간을 기준으로 LocalDate 값을 구함
        LocalDate now = LocalDate.now();
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
