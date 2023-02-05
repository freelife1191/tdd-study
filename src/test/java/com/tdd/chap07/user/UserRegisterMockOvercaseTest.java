package com.tdd.chap07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 모의 객체를 과하게 사용하지 않기
 */
public class UserRegisterMockOvercaseTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private UserRepository mockRepository = Mockito.mock(UserRepository.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, mockRepository, mockEmailNotifier);
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupId() {
        // 이미 같은 ID 존재하는 상황 만들기
        BDDMockito.given(mockRepository.findById("id"))
                .willReturn(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    /**
     * 결과를 확인하기 위해 테스트 코드가 복잡해짐
     */
    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        // 성공 여부 확인
        BDDMockito.then(mockRepository)
            // 호출 여부 확인
            .should()
            // save 메서드를 호출할 때 전달한 인자를 저장
            .save(captor.capture());

        User savedUser = captor.getValue();
        // 값이 유효한지 검증
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

}
