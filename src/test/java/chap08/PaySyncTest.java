package chap08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.chap08.payinfo.PayInfo;
import com.example.chap08.testable.MemoryPayInfoDao;
import com.example.chap08.testable.PaySync;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaySyncTest {
  // 대역 생성
  private MemoryPayInfoDao memoryPayInfoDao = new MemoryPayInfoDao();

  @Test
  void allDataSaved() throws IOException {
    PaySync paySync = new PaySync(memoryPayInfoDao);
    paySync.setFilePath("src/test/resources/c0111.csv");
    paySync.sync();

    // 대역을 이용한 결과 검증
    List<PayInfo> savedInfos = memoryPayInfoDao.getAll();
    assertEquals(2, savedInfos.size());
  }

}
