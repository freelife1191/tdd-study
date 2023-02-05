package com.tdd.chap07.user;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    // StubWeakPasswordChecker가 대역으로 동작할 수 있게 구현 추가
    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
