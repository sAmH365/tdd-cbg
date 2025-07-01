package com.example.chap08.testable;

import static com.example.chap08.subs.Grade.GOLD;

import com.example.chap08.subs.Product;
import com.example.chap08.subs.Subscription;
import java.time.LocalDate;

public class PointRule {

  public int calculate(Subscription s, Product p, LocalDate now) {
    int point = 0;
    if (s.isFinished(now)) {
      point += p.getDefaultPoint();
    } else {
      point += p.getDefaultPoint() + 10;
    }
    if (s.getGrade() == GOLD) {
      point += 100;
    }
    return point;
  }
}
