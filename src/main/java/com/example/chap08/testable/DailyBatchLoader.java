package com.example.chap08.testable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyBatchLoader {
  private String basePath = ".";
  private Times times = new Times();

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public void setTimes(Times times) {
    this.times = times;
  }

  public int load() {
    LocalDate date = times.today();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Path batchPath = Paths.get(basePath, date.format(formatter), "batch.txt");

    // ...batchPath에서 데이터를 읽어와 저장하는 코드

    try {
      return (int) Files.lines(batchPath).count();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
