package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class C_NumberTest {

    private C_Number CN = new C_Number(1, 1);

    @Test
    public void testCreateCnumber() {
        int ExpectedResult = CN.CreateCnumber(1, 1);
        int ActualResult = CN.getC_Number_t();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }
}