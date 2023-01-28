package com.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metConunts = 0;
        if (s.length() >= 8) metConunts++;
        if (meetsContainningNumberCriteria(s)) metConunts++;
        if (meetsContainingUppercaseCriteria(s)) metConunts++;
        if (metConunts == 1) return PasswordStrength.WEAK;
        if (metConunts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
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
