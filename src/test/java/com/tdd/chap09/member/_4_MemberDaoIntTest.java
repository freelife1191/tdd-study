package com.tdd.chap09.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class _4_MemberDaoIntTest {
    @Autowired
    MemberDao dao;

    @Test
    void findAll() {
        List<Member> members = dao.selectAll();
        assertNotNull(members);
    }
}
