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
 */
public class PaySync {
    private PayInfoDao payInfoDao = new PayInfoDao();
    private String filePath = "D:\\data\\pay\\cp0001.csv";

    /**
     * 생성자를 통해서 의존 대상을 주입하게 수정해서 테스트 가능하게 함
     */
    /*
    public PaySync(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }
    */

    /**
     * 세터를 이용해서 값을 교체 가능하게 함으로써 테스트가 쉬워짐
     */
    public void setPayInfoDao(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void sync() throws IOException {
        Path path = Paths.get(filePath);
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(
                            data[0], data[1], Integer.parseInt(data[2])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());

        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }

}
