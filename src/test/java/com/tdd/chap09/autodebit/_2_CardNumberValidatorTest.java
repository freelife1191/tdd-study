package com.tdd.chap09.autodebit;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * WireMock을 이용한 REST 클라이언트 테스트
 * https://wiremock.org/docs/download-and-installation/
 */
public class _2_CardNumberValidatorTest {

    // WireMock을 사용해 서버 API를 스텁으로 대체
    // 올바른 응답이나 타임아웃과 같은 상황에 대해 테스트할 수 있음
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        // 지정한 주소로 WireMockServer가 제공하는 HTTP 서버에 연결
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void valid() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("1234567890"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("ok"))
        );

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.VALID, validity);
    }

    @Test
    void timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse()
                        // 응답 시간을 5초 지연뒤에 응답을 전송하도록 설정
                        .withFixedDelay(5000))
        );

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT, validity);
    }
}
