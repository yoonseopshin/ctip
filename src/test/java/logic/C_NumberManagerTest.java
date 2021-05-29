package logic;

import org.junit.Assert;
import org.junit.Test;

public class C_NumberManagerTest {
    private CNumberManager CM = new CNumberManager();
    private CNumber Cn = new CNumber(1, 1);

    @Test
    public void testPopCnumber() {
        Cn.setC_Number_t(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult = Cn.getTitle_id();
        int ActualResult = CM.PopCnumber(971125);
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testCheckCnumber() {
        Cn.setC_Number_t(999999);
        CM.setM_Number(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult = 1;
        int ActualResult = CM.CheckCnumber(999999);
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = 0;
        ActualResult = CM.CheckCnumber(971125);
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = -1;
        ActualResult = CM.CheckCnumber(888888);
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testCheckCnumber2() {
        Cn.setC_Number_t(999999);
        CM.AddchCnumber(Cn);
        boolean ActualResult = CM.CheckCnumber2(999999);
        Assert.assertTrue(ActualResult);
        ActualResult = CM.CheckCnumber2(888888);
        Assert.assertFalse(ActualResult);
    }

    @Test
    public void testAddCnumber() {
        Cn.setC_Number_t(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult = 1;
        int ActualResult = CM.getC_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }


    @Test
    public void testAddchCnumber() {
        Cn.setC_Number_t(971125);
        CM.AddchCnumber(Cn);
        int ExpectedResult = 1;
        int ActualResult = CM.getCh_C_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }
}