package com.tdd.chap08.testable;

import com.tdd.chap08.payinfo.PayInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * [하드 코딩된 상수를 생성자나 메서드 파라미터로 받기]
 * 대역을 사용해서 테스트
 */
public class PaySyncTest {
    // 대역 생성
    private MemoryPayInfoDao memoryDao = new MemoryPayInfoDao();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryDao); // 대역으로 교체
        paySync.setFilePath("src/test/resources/c0111.csv");

        paySync.sync();

        // 대역을 이용한 결과 검증
        List<PayInfo> savedInfos = memoryDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}
