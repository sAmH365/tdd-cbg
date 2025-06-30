package com.example.chap08.nontestable;

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
    LocalDate now = LocalDate.now(); // <- 현재시간 기준으로 테스트를 진행하면, 싫행시점마다 결과가 달라져서 테스트가 힘듦 (어제는 성공이더라도 오늘은 실패할수 있다)
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
