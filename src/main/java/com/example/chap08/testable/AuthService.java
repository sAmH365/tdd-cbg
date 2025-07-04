package com.example.chap08.testable;

import com.example.chap08.auth.AuthUtil;

public class AuthService {
  private String authKey = "somekey";

  public int authenticate(String id, String pw) {
    boolean authorized = AuthUtil.authorize(authKey);
    if (authorized) {
      return AuthUtil.authenticate(id, pw);
    } else {
      return -1;
    }
  }
}
