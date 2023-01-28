package com.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다
 * 2개월 이상 요금을 납부할 수 있다
 * 10만 원을 납부하면 서비스를 1년 제공한다
 */
public class _3_ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019,3,1))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,4,1));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019,5,5))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,6,5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019,1,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,2,28));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019,5,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,6,30));
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2020,1,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2020,2,29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        // 첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 1만 원을 납부하면 다음 만료일은 2019-03-31이다 조건 추가
        PayData payData = PayData.builder()
            .firstBillingDate(LocalDate.of(2019,1,31))
            .billingDate(LocalDate.of(2019,2,28))
            .payAmount(10_000)
            .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        // 첫 납부일과 만료일 일자가 같지 않은 사례 추가
        PayData payData2 = PayData.builder()
            .firstBillingDate(LocalDate.of(2019,1,30))
            .billingDate(LocalDate.of(2019,2,28))
            .payAmount(10_000)
            .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));
    }

    private static void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}