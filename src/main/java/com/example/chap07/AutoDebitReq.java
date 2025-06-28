package com.example.chap07;

public class AutoDebitReq {

  private String cardNumber;
  private String userId;

  public AutoDebitReq(String cardNumber, String userId) {
    this.cardNumber = cardNumber;
    this.userId = userId;
  }

  public String getCardNumber() {
    return this.cardNumber;
  }

  public String getUserId() {
    return this.userId;
  }
}
