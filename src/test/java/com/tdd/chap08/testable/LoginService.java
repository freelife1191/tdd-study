package com.tdd.chap08.testable;

import com.tdd.chap08.auth.Customer;
import com.tdd.chap08.auth.CustomerRepository;
import com.tdd.chap08.auth.LoginResult;

/**
 * 외부 라이브러리는 직접 사용하지 말고 감싸서 사용하기
 */
public class LoginService {
    private AuthService authService = new AuthService();
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * 대역 사용이 어려운 외부 라이브러리를 직접 사용하지 않게 변경
     */
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}
