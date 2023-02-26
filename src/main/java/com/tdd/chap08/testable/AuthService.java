package com.tdd.chap08.testable;

import com.tdd.chap08.auth.AuthUtil;

/**
 * 외부 라이브러리를 감싼 클래스
 */
public class AuthService {
    String authKey = "somekey";

    int authenticate(String id, String pw) {

        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            return AuthUtil.authenticate(id, pw);
        } else {
            return -1;
        }
    }
}