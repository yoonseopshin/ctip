package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller c = new Controller();

    @Test
    public void testInit() {
        c.setBasket(10);
        c.setPayment(new Payment(c.getBasket(), 1));
        c.init();
        int ExpectedResult = -666;
        int ActualResult = c.getBasket();
        Assert.assertEquals(ExpectedResult, ActualResult);
        Assert.assertNull(c.getPayment());
    }
}