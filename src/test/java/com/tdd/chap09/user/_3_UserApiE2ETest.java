package com.tdd.chap09.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 스프링 부트의 내장 서버를 이용한 API 기능 테스트
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class _3_UserApiE2ETest {
    // 스프링 부트가 테스트 목적으로 제공하는 것으로 내장 서버에 연결하는 RestTemplate
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void weakPwResponse() {
        String reqBody = "{\"id\": \"id\", \"pw\": \"123\", \"email\": \"a@a.com\" }";
        RequestEntity<String> request =
                RequestEntity.post(URI.create("/users"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(reqBody);

        ResponseEntity<String> response = restTemplate.exchange(
                request,
                String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("WeakPasswordException"));
    }
}
