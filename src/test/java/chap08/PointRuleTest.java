package chap08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.chap08.subs.Grade;
import com.example.chap08.subs.Product;
import com.example.chap08.subs.Subscription;
import com.example.chap08.testable.PointRule;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointRuleTest {

  @Test
  void 만료전_GOLD등급은_130포인트() {
    PointRule pointRule = new PointRule();
    Subscription s = new Subscription(
        LocalDate.of(2025, 5, 5),
        Grade.GOLD
    );
    Product p = new Product();
    p.setDefaultPoint(20);

    int point = pointRule.calculate(s, p, LocalDate.of(2025, 5, 2));
    assertEquals(130, point);
  }
}
