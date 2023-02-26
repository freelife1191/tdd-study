package com.tdd.chap08.testable;

import com.tdd.chap08.auth.AuthUtil;
import com.tdd.chap08.auth.Customer;
import com.tdd.chap08.auth.CustomerRepository;
import com.tdd.chap08.auth.LoginResult;

/**
 * 외부 라이브러리는 직접 사용하지 말고 감싸서 사용하기
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
