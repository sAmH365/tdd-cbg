package com.example.chap07;

public class User {
  private String id;
  private String password;
  private String email;

  public User(String email, String id, String password) {
    this.email = email;
    this.id = id;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }
}
