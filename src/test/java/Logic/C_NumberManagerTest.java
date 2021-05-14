package Logic;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class C_NumberManagerTest {

  private C_NumberManager C_NumberManager;
  private C_Number C_Number;

  @Test
  public void popCnumber() {
    C_NumberManager = new C_NumberManager();
    C_Number = new C_Number();
    int num = C_Number.CreateCnumber(5, 0);
    C_NumberManager.C_List.put(0, C_Number);
    int expectedResult = C_Number.title_id;
    int actualResult = C_NumberManager.title_id;
    Assert.assertEquals(expectedResult, actualResult);
  }

  @Ignore
  @Test
  public void checkCnumber() {
    C_NumberManager = new C_NumberManager();
    C_Number = new C_Number();
    int num = C_Number.CreateCnumber(5, 0);
    C_NumberManager.C_List.put(0, C_Number);
    int expectedResult = 1;
    int actualResult = C_NumberManager.CheckCnumber(num);
    Assert.assertEquals(expectedResult, actualResult);
  }
}