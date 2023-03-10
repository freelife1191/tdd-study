package com.tdd.chap07.user;

/**
 * EmailNotifier의 스파이 대역 구현
 */
public class SpyEmailNotifier implements EmailNotifier {
    private boolean called;
    private String email;

    public boolean isCalled() {
        return called;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        // 테스트를 통과시키기 위한 처리
        this.called = true;
        this.email = email;
    }
}
