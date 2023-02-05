package com.tdd.chap07.autodebit;

/**
 * AutoDebitInfoRepository의 대역 구현
 */
public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {
    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}
