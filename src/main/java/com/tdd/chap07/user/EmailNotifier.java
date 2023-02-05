package com.tdd.chap07.user;

public interface EmailNotifier {
    // EmailNotifier의 이메일 발송 기능 추가
    void sendRegisterEmail(String email);
}
