package com.tdd.chap06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 외부 상황과 외부 결과
 */
public class _2_MathUtilsTest {

    @Test
    void dataFileSumTest() {
        File dataFile = new File("src/test/resources/datafile.txt");
        long sum = MathUtils.sum(dataFile);
        assertEquals(10L, sum);
    }

    @Test
    void dataFileSumTest2() {
        // 테스트 코드 안에 필요한 것이 다 있음
        givenDataFile("out/datafile.txt", "1","2","3","4");
        File dataFile = new File("src/test/resources/datafile.txt");
        long sum = MathUtils.sum(dataFile);
        assertEquals(10L, sum);
    }

    /**
     * 파일을 미리 만들지 않고 테스트 코드에서 상황에 맞는 파일을 생성하는 방법
     * @param path
     * @param lines
     */
    private void givenDataFile(String path, String... lines) {
        try {
            Path dataPath = Paths.get(path);
            System.out.println(dataPath);
            if (Files.exists(dataPath)) {
                Files.delete(dataPath);
            }
            Files.write(dataPath, Arrays.asList(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
