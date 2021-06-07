package logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class CNumberManagerTest {

  private CNumberManager CM = new CNumberManager();
  private CNumber Cn = new CNumber(1, 1);

  @Test
  public void testPopCNumber() {
    Cn.setCNumberT(971125);
    CM.addCNumber(Cn);
    int ExpectedResult = Cn.getTitleId();
    int ActualResult = CM.popCNumber(971125);
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testCheckCNumber() {
    Cn.setCNumberT(999999);
    CM.setMNumber(971125);
    CM.addCNumber(Cn);
    int ExpectedResult = 1;
    int ActualResult = CM.checkCNumber(999999);
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = 0;
    ActualResult = CM.checkCNumber(971125);
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = -1;
    ActualResult = CM.checkCNumber(888888);
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testCheckCNumber2() {
    Cn.setCNumberT(999999);
    CM.addChCNumber(Cn);
    boolean ActualResult = CM.checkCNumber2(999999);
    Assert.assertTrue(ActualResult);
    ActualResult = CM.checkCNumber2(888888);
    Assert.assertFalse(ActualResult);
  }

  @Test
  public void testAddCNumber() {
    Cn.setCNumberT(971125);
    CM.addCNumber(Cn);
    int ExpectedResult = 1;
    int ActualResult = CM.getCList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }


  @Test
  public void testAddChCNumber() {
    Cn.setCNumberT(971125);
    CM.addChCNumber(Cn);
    int ExpectedResult = 1;
    int ActualResult = CM.getChCList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  public void testGetMNumber() {
    CM.setMNumber(123456);
    int ExpectedResult = 123456;
    int ActualResult = CM.getMNumber();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  public void testGetCList() {
    CNumber cnumber = new CNumber(1, 1);
    cnumber.setcNumberT(111111);
    HashMap<Integer, CNumber> cList = new HashMap<>();
    cList.put(1,cnumber);
    Assert.assertEquals(cList,CM.getCList());
  }

  public void testGetChCList() {
  }


}