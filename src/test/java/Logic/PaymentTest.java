package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    Payment payment = new Payment(1, 1);
    ;

    @Test
    public void testCardPay() {
        int ActualResult = payment.CardPay(true);
        Assert.assertTrue(ActualResult > 0);
        int ExpectedResult = -3;
        ActualResult = payment.CardPay(false);
        Assert.assertEquals(ExpectedResult, ActualResult);
        payment = new Payment(1, 0);
        ExpectedResult = 0;
        ActualResult = payment.CardPay(true);
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = -3;
        ActualResult = payment.CardPay(false);
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testSmartPay() {
        int ActualResult = payment.SmartPay(true);
        Assert.assertTrue(ActualResult > 0);
        int ExpectedResult = -3;
        ActualResult = payment.SmartPay(false);
        Assert.assertEquals(ExpectedResult, ActualResult);
        payment = new Payment(1, 0);
        ExpectedResult = 0;
        ActualResult = payment.SmartPay(true);
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = -3;
        ActualResult = payment.SmartPay(false);
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testInit() {
        payment = new Payment(1, 1);
        payment.init();
        int ExpectedResult = -1;
        int ActualResult = payment.getTitle_id();
        Assert.assertEquals(ActualResult, ExpectedResult);
        ExpectedResult = -1;
        ActualResult = payment.getDVMid();
        Assert.assertEquals(ExpectedResult, ActualResult);
        assertNull(payment.getError_log());
    }
}