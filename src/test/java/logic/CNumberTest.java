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
  }

  public void testRandNumber() {
  }

  public void testGetTitleId() {
  }

  public void testSetTitleId() {
  }

  public void testGetDvmID() {
  }

  public void testSetDvmID() {
  }

  public void testGetCNumberT() {
  }

  public void testSetCNumberT() {
  }

  public void testGetcNumberT() {
  }

  public void testSetcNumberT() {
  }

  public void testGetRand() {
  }

  public void testSetRand() {
  }
}