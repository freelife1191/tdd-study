package com.tdd.chap08.nontestable;

import com.tdd.chap08.auth.AuthUtil;
import com.tdd.chap08.auth.Customer;
import com.tdd.chap08.auth.CustomerRepository;
import com.tdd.chap08.auth.LoginResult;

/**
 * [정적 메서드 사용]
 * 정적인 메서드로 인해 테스트하기 어려운 코드
 * AuthUtil 클래스가 인증 서버와 통신하는 경우 이 코드를 테스트하려면 동작하고 있는 인증 서버가 필요
 */
public class LoginService {
    private String authKey = "somekey";
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public LoginResult login(String id, String pw) {
        int resp = 0;
        // AuthUtil 클래스의 정적 메서드를 사용하고 있음
        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            // AuthUtil 클래스의 정적 메서드를 사용하고 있음
            resp = AuthUtil.authenticate(id, pw);
        } else {
            resp = -1;
        }
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}
