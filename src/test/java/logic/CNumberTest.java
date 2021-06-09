package logic;

import org.junit.Assert;
import org.junit.Test;

public class CNumberTest {

  private CNumber CN = new CNumber(1, 1);

  @Test
  public void testCreateCNumber() {
    int ExpectedResult = CN.createCNumber();
    int ActualResult = CN.getCNumberT();
    Assert.assertEquals(ExpectedResult, ActualResult);
    int Expectedlength = 6;
    int Actuallength = (int)(Math.log10(ExpectedResult)+1);
    Assert.assertEquals(Expectedlength, Actuallength);
  }

  @Test
  public void testRandNumber() {
    String ActualResult = CN.randNumber();
    Assert.assertNotNull(ActualResult);
  }

  @Test
  public void testGetTitleId() {
    CN.setTitleId(4);
    int ExpectedResult = 4;
    int ActualResult = CN.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetDvmID() {
    CN.setDvmID(3);
    int ExpectedResult = 3;
    int ActualResult = CN.getDvmID();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetCNumberT() {
    CN.setCNumberT(123456);
    int ExpectedResult = 123456;
    int ActualResult = CN.getCNumberT();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

}