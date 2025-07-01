package com.example.chap08.testable;

import com.example.chap08.payinfo.PayInfo;
import com.example.chap08.payinfo.PayInfoDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PaySync {

  private final PayInfoDao payInfoDao; // <- 의존대상을 직접생성해서 테스트를 어렵게 만듦

  private String filePath = "D:\\data\\pay\\cp0001.csv";


  public PaySync(PayInfoDao payInfoDao) {
    this.payInfoDao = payInfoDao;
  }


  public void sync() throws IOException {
    Path path = Paths.get(filePath);
    List<PayInfo> payInfos = Files.lines(path)
        .map(line -> {
          String[] data = line.split(",");
          PayInfo payInfo = new PayInfo(
              data[0], data[1], Integer.parseInt(data[2])
          );
          return payInfo;
        })
        .collect(Collectors.toList());

    payInfos.forEach(pi -> payInfoDao.insert(pi));
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
