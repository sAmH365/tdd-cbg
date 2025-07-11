package com.example.chap07;

public class StubCardNumberValidator extends CardNumberValidator{
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
      return CardValidity.INVALID;
    }

    if (invalidNo != null && theftNo.equals(cardNumber)) {
      return CardValidity.THEFT;
    }

    return CardValidity.VALID;
  }
}
