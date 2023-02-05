package com.tdd.chap07.autodebit;

import java.util.HashMap;
import java.util.Map;

/**
 * AutoDebitInfoRepository의 대역 구현
 */
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
