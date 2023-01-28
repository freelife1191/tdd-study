package com.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            // 첫 납부일과 납부일의 일자가 다르면 첫 납부일의 일자를 만료일의 일자로 사용
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                // 후보 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자 추가
                int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
