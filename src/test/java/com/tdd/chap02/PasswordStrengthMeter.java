package com.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null) return PasswordStrength.INVALID;
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = meetsContainningNumberCriteria(s);
        if (!containsNum) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
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
