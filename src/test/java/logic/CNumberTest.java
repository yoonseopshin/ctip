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

  public void testRandNumber() {
    int ExpectedResult = null;
    ExpectedResult = CN.randNumber();
    Assert.assertNotNull(ExpectedResult);
  }

  @Test
  public void testGetTitleId() {
    CNumber.setTitleId(4);
    int ExpectedResult = 4;
    int ActualResult = CNumber.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSetTitleId() {
    int ExpectedResult = 4;
    CNumber.setTitleId(2);
    ExpectedResult = 2;
    int ActualResult = CNumber.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetDvmID() {
    CNumber.setDvmID(3);
    int ExpectedResult = 3;
    int ActualResult = CNumber.getDvmID();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSetDvmID() {
    int ExpectedResult = 4;
    CNumber.setDvmID(2);
    ExpectedResult = 2;
    int ActualResult = CNumber.getDvmID();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetcNumberT() {
    CN.setCNumberT(123456);
    int ExpectedResult = 123456;
    int ActualResult = CN.getCNumberT();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSetcNumberT() {
    int ExpectedResult = 654321;
    CN.setCNumberT(123456);
    ExpectedResult = 123456;
    int ActualResult = CN.getCNumberT();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }


}