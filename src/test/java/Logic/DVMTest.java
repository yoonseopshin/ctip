package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DVMTest {
    private DVM dvm = new DVM(1, 1.0, 1.0);

    @Test
    public void testGetID() {
        dvm.setID(7);
        int ExpectedResult = 7;
        int ActualResult = dvm.getID();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testGetAddress_X() {
        dvm.setAddress_X(1.23456);
        Double ExpectedResult = 1.23456;
        Double ActualResult = dvm.getAddress_X();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testGetAddress_Y() {
        dvm.setAddress_Y(2.45678);
        Double ExpectedResult = 2.45678;
        Double ActualResult = dvm.getAddress_Y();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }
}