package com.tdd.chap08.nontestable;

import com.tdd.chap08.payinfo.PayInfo;
import com.tdd.chap08.payinfo.PayInfoDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [하드 코딩된 경로, 의존 객체를 직접 생성]
 * 파일 경로가 하드 코딩되어 있는 테스트 대상
 * 하드 코딩된 경로뿐만 아니라 하드 코딩된 IP 주소, 포트 번호도 테스트를 어렵게 만든다
 */
public class PaySync {
    // 의존 대상을 직접 생성
    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync() throws IOException {
        Path path = Paths.get("/data/pay/cp0001.csv");
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(
                            data[0], data[1], Integer.parseInt(data[1])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());

        // DB를 준비해야 하고 필요한 테이블도 만들어야 함
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}
