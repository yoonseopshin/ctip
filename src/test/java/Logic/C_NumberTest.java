package Logic;

import org.junit.*;

public class C_NumberTest {

  private final C_Number CN = new C_Number(1, 1);

  @Test
  public void testCreateCnumber() {
    Assert.assertEquals(CN.CreateCnumber(1, 1), CN.getC_Number_t());
  }
}