package chap03.payment;

import com.example.chap03.payment.ExpiryDateCalculator;
import com.example.chap03.payment.PayData;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {
/**
 * 1. 서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준, 한 달 뒤가 서비스 만료일이 된다.
 * 2. 2개월 이상 요금을 납부할 수 있다.
 * 3. 10만원을 납부하면 서비스를 1년 제공한다.
 */
  ExpiryDateCalculator cal = new ExpiryDateCalculator();

  @Test
  void 만원_납부하면_한달_뒤가_만료일이_됨() {

    int payAmount = 10_000;

    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 06, 26))
        .payAmount(payAmount)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2025, 07, 26));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 06, 25))
        .payAmount(payAmount)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2025, 07, 25));
  }

  @Test
  void 납부일과_한달_뒤_일자가_같지않음() {
    int payAmount = 10_000;

    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 31))
        .payAmount(payAmount)
        .build();
    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 5, 31))
        .payAmount(payAmount)
        .build();
    PayData payData3 = PayData.builder()
        .billingDate(LocalDate.of(2024, 1, 31))
        .payAmount(payAmount)
        .build();

    assertExpiryDate(payData1, LocalDate.of(2025, 2, 28));
    assertExpiryDate(payData2, LocalDate.of(2025, 6, 30));
    assertExpiryDate(payData3, LocalDate.of(2024, 2, 29));
  }

  @Test
  void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
    int payAmount = 10_000;

    PayData payData1 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 1, 31))
        .billingDate(LocalDate.of(2025, 2, 28))
        .payAmount(payAmount)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2025, 3, 31));

    PayData payData2 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 1, 30))
        .billingDate(LocalDate.of(2025, 2, 28))
        .payAmount(payAmount)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2025, 3, 30));

    PayData payData3 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 5, 31))
        .billingDate(LocalDate.of(2025, 6, 30))
        .payAmount(payAmount)
        .build();
    assertExpiryDate(payData3, LocalDate.of(2025, 7, 31));
  }

  @Test
  void 이만원_이상_납부하면_비례해서_만료일_계산() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 5, 1))
        .payAmount(20_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2025, 7, 1));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 5, 1))
        .payAmount(30_000)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2025, 8, 1));
  }

  @Test
  void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
    PayData payData1 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 1, 31))
        .billingDate(LocalDate.of(2025, 2, 28))
        .payAmount(20_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2025, 4, 30));

    PayData payData2 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 1, 31))
        .billingDate(LocalDate.of(2025, 2, 28))
        .payAmount(40_000)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2025, 6, 30));

    PayData payData3 = PayData.builder()
        .firstBillingDate(LocalDate.of(2025, 3, 31))
        .billingDate(LocalDate.of(2025, 4, 30))
        .payAmount(30_000)
        .build();
    assertExpiryDate(payData3, LocalDate.of(2025, 7, 31));
  }

  @Test
  void 십만원을_납부하면_1년_제공() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(100_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2026, 1, 28));
  }

  @Test
  void 십만원이상을_납부하면_1년에더해서_만원마다_1개월씩제공() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(130_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2026, 4, 28));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(140_000)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2026, 5, 28));

    PayData payData3 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(170_000)
        .build();
    assertExpiryDate(payData3, LocalDate.of(2026, 8, 28));
  }

  @Test
  void 이십만원이상을납부하면_2년에더해서_만원마다_1개월씩_제공() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(200_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2027, 1, 28));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(230_000)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2027, 4, 28));
  }

  @Test
  void 삼십만원단위로제공테스트() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(300_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2028, 1, 28));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2025, 1, 28))
        .payAmount(350_000)
        .build();
    assertExpiryDate(payData2, LocalDate.of(2028, 6, 28));
  }

  @Test
  void 윤달마지막날_10만원납부() {
    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2024, 2, 29))
        .payAmount(100_000)
        .build();
    assertExpiryDate(payData1, LocalDate.of(2025, 2, 28));
  }

  private void assertExpiryDate(PayData data, LocalDate expectedExpiryDate) {
    LocalDate realExpiryDate = cal.calculateExpiryDate(data);
    Assertions.assertEquals(expectedExpiryDate, realExpiryDate);
  }
}
