package com.tdd.chap08.testable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 시간을 구하는 기능을 별도로 분리하면 테스트를 하기 수월해진다
 */
public class DailyBatchLoader {
    // Times 대역으로 시간을 구하는 기능을 구성
    private Times times = new Times();
    private String basePath = ".";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public int load() {
        LocalDate date = times.today();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Path batchPath = Paths.get(basePath, date.format(formatter), "batch.txt");

        // ...batchPath에서 데이터를 읽어와 저장하는 코드
        try {
            return (int) Files.lines(batchPath).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
