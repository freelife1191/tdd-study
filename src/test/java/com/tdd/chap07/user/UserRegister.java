package com.tdd.chap07.user;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        // 이미 존재하는 ID를 가진 사용자가 존재하는지 확인
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        // 생성한 User 객체를 리포지토리에 저장해서 테스트 통과
        userRepository.save(new User(id, pw, email));
    }
}
