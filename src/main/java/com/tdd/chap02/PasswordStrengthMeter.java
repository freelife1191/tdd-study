package com.tdd.chap02;

/**
 * 암호가 null 이거나 빈 문자열이면 암호강도는 INVALID 이다
 * 충족하는 규칙 개수를 구한다
 * 충족하는 규칙 개수가 1개 이하면 암호 강도는 WEAK 이다
 * 충족하는 규칙 개수가 2개면 암호 강도는 NORMAL 이다
 * 이 외 경우(즉 충족하는 규칙 개수가 2개보다 크면) 암호 강도는 STRONG 이다
 */
public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metConunts = getMetCriteriaCounts(s);
        if (metConunts <= 1) return PasswordStrength.WEAK;
        if (metConunts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private static int getMetCriteriaCounts(String s) {
        int metConunts = 0;
        if (s.length() >= 8) metConunts++;
        if (meetsContainningNumberCriteria(s)) metConunts++;
        if (meetsContainingUppercaseCriteria(s)) metConunts++;
        return metConunts;
    }

    private static boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean meetsContainningNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
