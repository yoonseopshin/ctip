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
}