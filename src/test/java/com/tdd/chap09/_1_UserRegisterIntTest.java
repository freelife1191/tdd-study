package com.tdd.chap09;

import com.tdd.chap09.user.DupIdException;
import com.tdd.chap09.user.UserRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 테스트 지원 기능을 이용한 UserRegister 통합 테스트
 */
@SpringBootTest
public class _1_UserRegisterIntTest {
    @Autowired
    private UserRegister register;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 동일ID가_이미_존재하면_익셉션() {
        // 상황
        jdbcTemplate.update("insert into `user` values (?,?,?) on duplicate key update password = ?, email = ?",
                "cbk", "pw", "cbk@cbk.com", "pw", "cbk@cbk.com");

        // 실행, 결과 확인
        assertThrows(DupIdException.class,
                () -> register.register("cbk", "strongpw", "email@email.com")
        );
    }

    @Test
    void 유저_조회() {
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from `user` where id = ?", "cbk");
        rs.next();
        System.out.println("id: "+rs.getString("id"));
        System.out.println("email: "+rs.getString("email"));
        System.out.println("password: "+rs.getString("password"));
        assertEquals("cbk@cbk.com", rs.getString("email"));
    }

    @Test
    void 존재하지_않으면_저장함() {
        // 상황
        jdbcTemplate.update("delete from `user` where id = ?", "cbk");
        // 실행
        register.register("cbk", "strongpw", "email@email.com");
        // 결과 확인
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from `user` where id = ?", "cbk");
        rs.next();
        assertEquals("email@email.com", rs.getString("email"));
    }
}
