package logic;

import org.junit.Assert;
import org.junit.Test;

public class C_NumberTest {

    private CNumber CN = new CNumber(1, 1);

    @Test
    public void testCreateCnumber() {
        int ExpectedResult = CN.createCNumber();
        int ActualResult = CN.getCNumberT();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }
}