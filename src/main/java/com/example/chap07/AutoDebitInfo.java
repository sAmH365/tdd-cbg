package com.example.chap07;

import java.time.LocalDateTime;

public class AutoDebitInfo {

  private String userId;
  private String cardNumber;
  private LocalDateTime localDateTime;


  public AutoDebitInfo(String userId, String cardNumber, LocalDateTime localDateTime) {
    this.userId = userId;
    this.cardNumber = cardNumber;
    this.localDateTime = localDateTime;
  }

  public void changeCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
}
