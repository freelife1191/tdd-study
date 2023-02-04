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
        File dataFile = new File("badpath.txt");
        assertThrows(IllegalArgumentException.class,
            () -> MathUtils.sum(dataFile)
        );
    }

}
