package com.example.chap03.payment;

import java.time.LocalDate;

public class PayData {
  private LocalDate firstBillingDate;
  private LocalDate billingDate;
  private int payAmount;

  public LocalDate getBillingDate() {
    return billingDate;
  }

  public LocalDate getFirstBillingDate() {
    return firstBillingDate;
  }

  public int getPayAmount() {
    return payAmount;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private PayData data = new PayData();

    public Builder billingDate (LocalDate billingDate) {
      data.billingDate = billingDate;
      return this;
    }

    public Builder firstBillingDate(LocalDate firstBillingDate) {
      data.firstBillingDate = firstBillingDate;
      return this;
    }

    public Builder payAmount (int payAmount) {
      data.payAmount = payAmount;
      return this;
    }

    public PayData build() {
      return data;
    }
  }
}
