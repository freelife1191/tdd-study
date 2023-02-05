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
 * 모의 객체로 스텁과 스파이 대체
 */
public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier =
        // 인자로 전달받은 타입의 모의 객체를 생성
        Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        BDDMockito
            // "pw" 인자를 사용해서 모의 객체의 checkPasswordWeak 메서드를 호출하면
            .given(mockPasswordChecker.checkPasswordWeak("pw"))
            // 결과로 true를 리턴하라
            .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        // 인자로 전달한 mockPasswordChecker 모의 객체의
        BDDMockito.then(mockPasswordChecker)
                // 특정 메서드가 호출됐는지 검증하는데
                .should()
                // 임의의 String 타입 인자를 이용해서 checkPasswordWeak() 메서드 호출 여부를 확인한다
                .checkPasswordWeak(Mockito.matches("pw"));
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupId() {
        // 이미 같은 ID 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    /**
     * 모의 객체의 메서드를 호출할 때 전달한 인자를 구하는 코드
     */
    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        // ArgumentCaptor는 모의 객체를 메서드를 호출할 때 전달한 객체를 담는 기능을 제공
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
            // 모의 객체의 메서드가 호출됐는지 확인
            .should()
            // capture 메서드를 사용하면 메서드를 호출할 때 전달한 인자가 ArgumentCaptor에 담긴다
            .sendRegisterEmail(captor.capture());

        // getValue 메서드를 사용해서 보관한 인자를 구할 수 있다
        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
