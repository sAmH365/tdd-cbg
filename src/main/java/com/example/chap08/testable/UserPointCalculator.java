package com.example.chap08.testable;

import static com.example.chap08.subs.Grade.GOLD;

import com.example.chap08.subs.NoSubscriptionException;
import com.example.chap08.subs.Product;
import com.example.chap08.subs.ProductDao;
import com.example.chap08.subs.Subscription;
import com.example.chap08.subs.SubscriptionDao;
import com.example.chap08.subs.User;
import java.time.LocalDate;

/**
 * 실행 시점마다 결과가 달라지는 코드는 테스트를 어렵게 만든다
 */
public class UserPointCalculator {
  private SubscriptionDao subscriptionDao;
  private ProductDao productDao;

  public UserPointCalculator(SubscriptionDao subscriptionDao,
      ProductDao productDao) {
    this.subscriptionDao = subscriptionDao;
    this.productDao = productDao;
  }

  public int calculatePoint(User u) {
    Subscription s = subscriptionDao.selectByUser(u.getId());
    if (s == null) throw new NoSubscriptionException();
    Product p = productDao.selectById(s.getProductId());
    LocalDate now = LocalDate.now();
    return new PointRule().calculate(s, p, now);
  }
}
