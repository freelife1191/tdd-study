package com.tdd.chap08.testable;

import com.tdd.chap08.payinfo.PayInfo;
import com.tdd.chap08.payinfo.PayInfoDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [하드 코딩된 상수를 생성자나 메서드 파라미터로 받기]
 * 하드 코딩된 경로를 파라미터로 전달받아 테스트 가능하게 변경
 */
public class PaySync2 {
    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(
                            data[0], data[1], Integer.parseInt(data[1])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());

        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }

}
