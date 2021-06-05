package logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

  Payment payment = new Payment(1, 1);

  @Test
  public void testCardPay() {
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
    assertNull(payment.getErrorLog());
  }

  @Test
  public void testGetTitleId() {
    Payment.setTitleId(4);
    int ExpectedResult = 4;
    int ActualResult = Payment.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSetTitleId() {
    int ExpectedResult = 4;
    Payment.setTitleId(2);
    ExpectedResult = 2;
    int ActualResult = Payment.getTitleId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetDvmId() {
    Payment.setDvmId(3);
    int ExpectedResult = 3;
    int ActualResult = Payment.getDvmId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testSetDvmId() {
    int ExpectedResult = 4;
    Payment.setDvmId(2);
    ExpectedResult = 2;
    int ActualResult = Payment.getDvmId();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

    public void testGetErrorLog() {
      Payment.setErrorLog("hello");
      String ExpectedResult = "hello";
      String ActualResult = Payment.getErrorLog();
      Assert.assertEquals(ExpectedResult, ActualResult);
    }

    public void testSetErrorLog() {
      String ExpectedResult= null;
      Payment.setErrorLog("hello");
      ExpectedResult = "hello";
      String ActualResult = Payment.getErrorLog();
      Assert.assertNotNull(ExpectedResult);
      Assert.assertEquals(ExpectedResult, ActualResult);
    }
}