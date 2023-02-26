package com.tdd.chap08.testable;

import com.tdd.chap08.subs.NoSubscriptionException;
import com.tdd.chap08.subs.Product;
import com.tdd.chap08.subs.ProductDao;
import com.tdd.chap08.subs.Subscription;
import com.tdd.chap08.subs.SubscriptionDao;
import com.tdd.chap08.subs.User;

import java.time.LocalDate;

/**
 * 포인트 계산 기능 자체를 대역으로 변경하고 싶다면 '의존 대상을 주입 받기'에서 설명한 것 처럼
 * 세터를 이용해서 의존 대상을 주입
 */
public class UserPointCalculator {
    private PointRule pointRule = new PointRule(); // 기본 구현을 사용
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    // 별도로 분리한 계산 기능을 주입할 수 있는 세터 추가
    // 테스트 코드에서 대역으로 계산 기능을 대체할 수 있게 함
    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
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
        return PointRule.calculate(s, p, now);
    }

}
