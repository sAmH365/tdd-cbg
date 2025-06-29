package com.example.chap07;

public interface AutoDebitInfoRepository {

  AutoDebitInfo findOne(String userId);

  void save(AutoDebitInfo newInfo);
}
