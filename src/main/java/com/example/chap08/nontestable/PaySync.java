package com.example.chap08.nontestable;

import com.example.chap08.payinfo.PayInfo;
import com.example.chap08.payinfo.PayInfoDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 하드코딩된 경로와 의존객체를 직접생성하는것은 테스트를 어렵게 만든다.
 *
 * 이 코드를 테스트 하려면 PayInfoDao가 동작하는데 필요한 모든 환경 구성 필요 (DB준비 + 테이블필요)
 * 중복데이터 문제도 테스트를 어렵게 만든다
 */
public class PaySync {
  private PayInfoDao payInfoDao = new PayInfoDao(); // <- 의존대상을 직접생성해서 테스트를 어렵게 만듦

  public void sync() throws IOException {
    Path path = Paths.get("D:\\data\\pay\\cp0001.csv"); // <- 윈도우에 D드라이브가 없거나, 다른 os환경에서는 테스트 불가능
    List<PayInfo> payInfos = Files.lines(path)
        .map(line -> {
          String[] data = line.split(",");
          PayInfo payInfo = new PayInfo(
              data[0], data[1], Integer.parseInt(data[1])
          );
          return payInfo;
        })
        .collect(Collectors.toList());

    payInfos.forEach(pi -> payInfoDao.insert(pi));
  }
}
