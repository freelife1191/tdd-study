package com.tdd.chap07.autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DB 대신 맵을 이용해서 자동이체 정보를 저장하는
 * MemoryAutoDebitInfoRepository를 이용한 테스트
 */
public class AutoDebitRegister_Fake_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        // 메모리를 이용한 대역 객체인 MemoryAutoDebitInfoRepository를 생성한다
        repository = new MemoryAutoDebitInfoRepository();
        // 테스트 대상인 AutoDebitRegister 객체를 생성할 때 대역을 전달한다
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        // 이미 자동이체 정보가 등록되어 있는 상황을 만들기 위해 대역을 사용한다.
        // 사용자 아이디 "user1"에 대한 자동이체 정보를 저장한다
        repository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        // "user1" 아이디에 대해 다른 카드번호를 사용해서 자동이체 등록 기능을 실행한다
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    /**
     * 아직 자동이체 정보가 등록되어 있지 않을 때 새로운 정보가 올바르게 등록되는지 검사
     */
    @Test
    void notYetRegistered_newInfoRegistered() {
        // 자동이체 등록 기능 실행
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        // 자동이체 등록 기능 실행 후 대역에 보관된 자동이체 정보를 구해서 값이 올바르게 변경되었는지 확인한다
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }
}
