package com.example.chap08.testable;

import com.example.chap08.payinfo.PayInfo;
import com.example.chap08.payinfo.PayInfoDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPayInfoDao extends PayInfoDao {

  private final Map<String, PayInfo> infos = new HashMap<>();

  @Override
  public void insert(PayInfo pi) {
    infos.put(pi.getTrNum(), pi);
  }

  public List<PayInfo> getAll() {
    return new ArrayList<>(infos.values());
  }
}
