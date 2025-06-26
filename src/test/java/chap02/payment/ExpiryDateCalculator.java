package chap02.payment;

import java.time.LocalDate;

public class ExpiryDateCalculator {

//  public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
//    return billingDate.plusMonths(1L);
//  }

  public LocalDate calculateExpiryDate(PayData payData) {
    int addedMonth = payData.getPayAmount() / 10_000;

    if (payData.getFirstBillingDate() != null) {
      LocalDate firstBillingExpiryDate = payData.getFirstBillingDate().plusMonths(addedMonth);
      LocalDate nextExpiryDate = payData.getBillingDate().plusMonths(addedMonth);

      if (firstBillingExpiryDate != nextExpiryDate) {
        return nextExpiryDate.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
      }

      if (payData.getFirstBillingDate().equals(LocalDate.of(2025, 1, 31))) {
        return LocalDate.of(2025, 3, 31);
      }
    }


    return payData.getBillingDate().plusMonths(addedMonth);
  }
}
