package com.example.chap08.nontestable;

import com.example.chap08.auth.AuthUtil;
import com.example.chap08.auth.Customer;
import com.example.chap08.auth.CustomerRepository;
import com.example.chap08.auth.LoginResult;

/**
 * 정적메서드의 사용은 테스트를 어렵게 만들수도 있다.
 */
public class LoginService {
  private String authKey = "somekey";
  private CustomerRepository customerRepo;

  public LoginService(CustomerRepository customerRepo) {
    this.customerRepo = customerRepo;
  }

  public LoginResult login(String id, String pw) {
    int resp = 0;
    boolean authorized = AuthUtil.authorize(authKey); // <- AuthUtil클래스가 인증서버와 통신하는경우 정적메서드 사용은 테스트를 어렵게 만듦
    if (authorized) {
      resp = AuthUtil.authenticate(id, pw);
    } else {
      resp = -1;
    }
    if (resp == -1) return LoginResult.badAuthKey();

    if (resp == 1) {
      Customer c = customerRepo.findOne(id);
      return LoginResult.authenticated(c);
    } else {
      return LoginResult.fail(resp);
    }
  }

}
