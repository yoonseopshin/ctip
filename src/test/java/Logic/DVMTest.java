package Logic;

import org.junit.*;

import static org.junit.Assert.*;

public class DVMTest {

  private final DVM dvm = new DVM(1, 0.5, 0.7);

  @Test
  public void testGetX() {
    Assert.assertEquals(Double.toString(0.5), Double.toString(dvm.getX()));
  }

  @Test
  public void testGetY() {
    Assert.assertEquals(Double.toString(0.7), Double.toString(dvm.getY()));
  }
}