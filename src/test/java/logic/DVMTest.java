package logic;

import org.junit.Assert;
import org.junit.Test;

public class DVMTest {

  private DVM dvm = new DVM(1, 1.0, 1.0);

  @Test
  public void testGetID() {
    dvm.setId(7);
    int ExpectedResult = 7;
    int ActualResult = dvm.getId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }


  @Test
  public void testGetAddressX() {
    dvm.setAddressX(1.23456);
    Double ExpectedResult = 1.23456;
    Double ActualResult = dvm.getAddressX();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetAddressY() {
    dvm.setAddressY(2.45678);
    Double ExpectedResult = 2.45678;
    Double ActualResult = dvm.getAddressY();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetCurrentID() {
    DVM.setCurrentID(1);
    Assert.assertEquals(1, DVM.getCurrentID());
  }

  @Test
  public void testGetCurrentX() {
    DVM.setCurrentX(1.0);
    Assert.assertEquals(Double.toString(1.0),Double.toString(DVM.getCurrentX()));
  }

  @Test
  public void testGetCurrentY() {
    DVM.setCurrentY(1.0);
    Assert.assertEquals(Double.toString(1.0),Double.toString(DVM.getCurrentY()));
  }
}