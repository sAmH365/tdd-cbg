package com.example.chap07;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository{

  private Map<String, AutoDebitInfo> infos = new HashMap<>();

  @Override
  public void save(AutoDebitInfo newInfo) {
    infos.put(newInfo.getUserId(), newInfo);
  }

  @Override
  public AutoDebitInfo findOne(String userId) {
    return infos.get(userId);
  }
}
