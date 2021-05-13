package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {
    Payment payment;
    @Test
    public void cardPay() {
        payment=new Payment(1,1);
        Assert.assertEquals(true,(payment.CardPay(true))>0);
        Assert.assertEquals(-3,payment.CardPay(false));
        payment=new Payment(1,0);
        Assert.assertEquals(0,payment.CardPay(true));
        Assert.assertEquals(-3,payment.CardPay(false));
    }

    @Test
    public void smartPay() {
        payment=new Payment(1,1);
        Assert.assertEquals(true,(payment.SmartPay(true))>0);
        Assert.assertEquals(-3,payment.SmartPay(false));
        payment=new Payment(1,0);
        Assert.assertEquals(0,payment.SmartPay(true));
        Assert.assertEquals(-3,payment.SmartPay(false));
    }

    @Test
    public void init() {
        payment=new Payment(1,1);
        payment.init();
        Assert.assertEquals(-1,payment.title_id);
        Assert.assertEquals(-1,payment.DVMid);
        Assert.assertEquals(null,payment.Error_log());


    }
}