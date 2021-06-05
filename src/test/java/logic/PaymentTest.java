package logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

  Payment payment = new Payment(1, 2);

  @Test
  public void testCardPay() {
    Controller.getCm().getChCList().clear();
    int ActualResult = payment.cardPay(true);
    Assert.assertTrue(ActualResult > 0);
    int ExpectedResult = -3;
    ActualResult = payment.cardPay(false);
    Assert.assertEquals(ExpectedResult, ActualResult);
    payment = new Payment(1, 0);
    ExpectedResult = 0;
    ActualResult = payment.cardPay(true);
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = -3;
    ActualResult = payment.cardPay(false);
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSmartPay() {
    Controller.getCm().getChCList().clear();
    int ActualResult = payment.smartPay(true);
    Assert.assertTrue(ActualResult > 0);
    int ExpectedResult = -3;
    ActualResult = payment.smartPay(false);
    Assert.assertEquals(ExpectedResult, ActualResult);
    payment = new Payment(1, 0);
    ExpectedResult = 0;
    ActualResult = payment.smartPay(true);
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = -3;
    ActualResult = payment.smartPay(false);
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testInit() {
    payment = new Payment(1, 1);
    payment.init();
    int ExpectedResult = -1;
    int ActualResult = payment.getTitleId();
    Assert.assertEquals(ActualResult, ExpectedResult);
    ExpectedResult = -1;
    ActualResult = payment.getDVMId();
    Assert.assertEquals(ExpectedResult, ActualResult);
    Assert.assertEquals("", payment.getErrorLog());
  }

  @Test
  public void testGetTitleId() {
    payment.setTitleId(4);
    int ExpectedResult = 4;
    int ActualResult = payment.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetDvmId() {
    payment.setDVMId(3);
    int ExpectedResult = 3;
    int ActualResult = payment.getDVMId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetErrorLog() {
    payment.setErrorLog("hello");
    String ExpectedResult = "hello";
    String ActualResult = payment.getErrorLog();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

}