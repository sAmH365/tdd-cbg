package com.example.chap03.payment;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

  public LocalDate calculateExpiryDate(PayData payData) {
    int monthlyPlanAmount = 10_000;
    int yearPlanAmount = 100_000;

    int addedMonth = payData.getPayAmount() / monthlyPlanAmount;
    if (payData.getPayAmount() >= yearPlanAmount) {
      int yearOfService = payData.getPayAmount() / yearPlanAmount;
      int leftAmount = payData.getPayAmount() - (yearPlanAmount * yearOfService);
      addedMonth = (12 * yearOfService) + (leftAmount / monthlyPlanAmount);
    }

    if (payData.getFirstBillingDate() != null) {
      return expiryDateUsingFirstBillingDate(payData, addedMonth);
    } else {
      return payData.getBillingDate().plusMonths(addedMonth);
    }
  }

  private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonth) {
    LocalDate firstBillingExpiryDate = payData.getFirstBillingDate().plusMonths(addedMonth);
    LocalDate nextExpiryDate = payData.getBillingDate().plusMonths(addedMonth);

    final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
    if (isSameDayOfMonth(nextExpiryDate, dayOfFirstBilling)) {
      final int dayLenOfCandiMon = lastDayOfMonth(nextExpiryDate);

      if (dayLenOfCandiMon < firstBillingExpiryDate.getDayOfMonth()) {
        return nextExpiryDate.withDayOfMonth(dayLenOfCandiMon);
      }
      return nextExpiryDate.withDayOfMonth(dayOfFirstBilling);
    }

    return nextExpiryDate.withDayOfMonth(dayOfFirstBilling);
  }

  private boolean isSameDayOfMonth(LocalDate nextExpiryDate, int dayOfFirstBilling) {
    return dayOfFirstBilling != nextExpiryDate.getDayOfMonth();
  }

  private int lastDayOfMonth(LocalDate nextExpiryDate) {
    return YearMonth.from(nextExpiryDate).lengthOfMonth();
  }
}
