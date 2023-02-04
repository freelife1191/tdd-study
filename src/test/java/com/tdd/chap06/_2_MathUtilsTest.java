package com.tdd.chap06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 외부 상황과 외부 결과
 */
public class _2_MathUtilsTest {

    @DisplayName("파일이 존재하지 않으면 IllegalArgumentException 발생")
    @Test
    void noDataFile_Then_Exception() {
        // 명시적으로 파일이 없는 상황 만들기
        givenNoFile("badpath.txt");

        File dataFile = new File("badpath.txt");
        assertThrows(IllegalArgumentException.class,
            () -> MathUtils.sum(dataFile)
        );
    }

    /**
     * 해당 경로에 파일이 존재하는지 검사해서 존재할 경우 해당 파일을 삭제
     * @param path
     */
    private void givenNoFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted)
                throw new RuntimeException("fail givenNoFile: " + path);
        }
    }

}
