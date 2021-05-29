package logic;

import org.junit.Assert;
import org.junit.Test;

public class C_NumberTest {

    private CNumber CN = new CNumber(1, 1);

    @Test
    public void testCreateCnumber() {
        int ExpectedResult = CN.CreateCnumber();
        int ActualResult = CN.getC_Number_t();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }
}