package com.example.chap07;

public class RegisterResult {
  public CardValidity validity;

  public CardValidity getValidity() {
    return validity;
  }

  public static RegisterResult error(CardValidity validity) {
    return null;
  }

  public static RegisterResult success() {
    return null;
  }
}
