package com.tdd.chap07.autodebit;

/**
 * CardNumberValidator의 대역 구현
 * 단순한 구현으로 실제 구현을 대체
 */
public class StubCardNumberValidator extends CardNumberValidator {
    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            // invalidNo 필드와 동일한 카드 번호면 INVALID
            return CardValidity.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }
        // 아니면 VALID
        return CardValidity.VALID;
    }
}
