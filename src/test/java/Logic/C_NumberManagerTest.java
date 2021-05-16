package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class C_NumberManagerTest {
    private C_NumberManager CM= new C_NumberManager();
    private C_Number Cn=new C_Number(1,1);

    @Test
    public void testPopCnumber() {
        Cn.setC_Number_t(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult=Cn.getTitle_id();
        int ActualResult=CM.PopCnumber(971125);
        Assert.assertEquals(ExpectedResult,ActualResult);
    }

    @Test
    public void testCheckCnumber() {
        Cn.setC_Number_t(999999);
        CM.setM_Number(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult=1;
        int ActualResult=CM.CheckCnumber(999999);
        Assert.assertEquals(ExpectedResult,ActualResult);
        ExpectedResult=0;
        ActualResult=CM.CheckCnumber(971125);
        Assert.assertEquals(ExpectedResult,ActualResult);
        ExpectedResult=-1;
        ActualResult=CM.CheckCnumber(888888);
        Assert.assertEquals(ExpectedResult,ActualResult);
    }

    @Test
    public void testAddCnumber() {
        Cn.setC_Number_t(971125);
        CM.AddCnumber(Cn);
        int ExpectedResult=1;
        int ActualResult=CM.getC_List().size();
        Assert.assertEquals(ExpectedResult,ActualResult);
    }
}